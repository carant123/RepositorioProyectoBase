package com.proyectobase.data.di.koin

import biz.belcorp.ffvv.data.preferences.SharedPreferencesAuth
import com.proyectobase.data.BuildConfig
import com.proyectobase.data.exception.UnauthorizedException
import com.proyectobase.data.net.GoogleApi
import com.proyectobase.data.net.UsuarioApi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val HEADER_NAME_CONTENT_TYPE = "Content-Type"
const val HEADER_NAME_APP_VERSION = "appVersion"

const val HEADER_VALUE_JSON = "application/json"
const val HEADER_VALE_URL_ENCODED = "application/x-www-form-urlencoded"

const val NAMED_USUARIO = "Usuario"
const val HOST_GOOGLE = "https://maps.googleapis.com"

val networkModule = module {

    single (name = NAMED_USUARIO) {retrofitUsuario()}
    single { provideUsuarioApi(get(name = NAMED_USUARIO)) }

}

/* Retrofit */

private fun retrofitUsuario(): Retrofit {
    return createRetrofit(createOkHttpClient(), BuildConfig.HOST)
}

/* API's */

private fun provideUsuarioApi(retrofit: Retrofit): UsuarioApi {
    return retrofit.create(UsuarioApi::class.java)
}

/* Utils */

private fun createHttpClientTokenAuth(): OkHttpClient {
    return createOkHttpClient {
        header(HEADER_NAME_CONTENT_TYPE, HEADER_VALE_URL_ENCODED)
    }
}

private fun createHttpClientAuth(preferences: SharedPreferencesAuth): OkHttpClient {
    return createOkHttpClient {
        header(HEADER_NAME_CONTENT_TYPE, HEADER_VALUE_JSON)
    }
}

private fun createRetrofit(httpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
}

private fun createOkHttpClient(f: (Request.Builder.() -> Request.Builder)? = null): OkHttpClient {
    return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val appVersion = BuildConfig.VERSION_NAME
                val requestBuilder = original.newBuilder()
                val request = (f?.invoke(requestBuilder) ?: requestBuilder)
                        .header(HEADER_NAME_APP_VERSION, appVersion)

                chain.proceed(request.build())
            }
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .readTimeout(3, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .build()
}