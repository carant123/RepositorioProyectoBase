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
const val HEADER_NAME_ACCEPT = "Accept"
const val HEADER_NAME_AUTHORIZATION = "Authorization"
const val HEADER_NAME_APP_VERSION = "appVersion"
const val HEADER_NAME_AUTH = "typeAuth"

const val HEADER_VALUE_JSON = "application/json"
const val HEADER_VALE_URL_ENCODED = "application/x-www-form-urlencoded"
const val HEADER_VALUE_BEARER = "Bearer"
const val HEADER_VALUE_AUTH = "31e41dbf-72d8-422b-ab3c-58812e7bf4ab"

const val NAMED_AUTH = "Auth"
const val NAMED_NO_AUTH = "NoAuth"
const val NAMED_TOKEN_AUTH = "TokenAuth"
const val NAMED_SB2 = "SomosBelcorp"
const val NAMED_GOOGLE = "Google"

const val NAMED_USUARIO = "Usuario"

const val HOST_GOOGLE = "https://maps.googleapis.com"

val networkModule = module {

//    single(name = NAMED_NO_AUTH) { retrofitMobileNoAuth() }
//    single(name = NAMED_TOKEN_AUTH) { retrofitTokenAuth() }
//    single(name = NAMED_AUTH) { retrofitMobileAuth(get()) }
//    single(name = NAMED_SB2) { retrofitSomosBelcorp() }
    //single(name = NAMED_GOOGLE) { retrofitGoogle() }
    single (name = NAMED_USUARIO) {retrofitUsuario()}

//    single { provideUserApi(get(name = NAMED_AUTH)) }
//    single { provideGoogleApi(get(name = NAMED_GOOGLE)) }
    single { provideUsuarioApi(get(name = NAMED_USUARIO)) }


}

/* Retrofit */

private fun retrofitMobileNoAuth(): Retrofit {
    return createRetrofit(createOkHttpClient(), BuildConfig.HOST)
}

private fun retrofitUsuario(): Retrofit {
    return createRetrofit(createOkHttpClient(), BuildConfig.HOST)
}

private fun retrofitTokenAuth(): Retrofit {
    return createRetrofit(createHttpClientTokenAuth(), BuildConfig.HOST)
}

private fun retrofitMobileAuth(preferences: SharedPreferencesAuth): Retrofit {
    return createRetrofit(createHttpClientAuth(preferences), BuildConfig.HOST)
}

private fun retrofitSomosBelcorp(): Retrofit {
    return createRetrofit(createOkHttpClient(), BuildConfig.API_SB)
}

private fun retrofitGoogle(): Retrofit {
    return createRetrofit(createOkHttpClient(), HOST_GOOGLE)
}

/* API's */

private fun provideUsuarioApi(retrofit: Retrofit): UsuarioApi {
    return retrofit.create(UsuarioApi::class.java)
}

private fun provideGoogleApi(retrofit: Retrofit): GoogleApi {
    return retrofit.create(GoogleApi::class.java)
}

/* Utils */

private fun createHttpClientTokenAuth(): OkHttpClient {
    return createOkHttpClient {
        header(HEADER_NAME_CONTENT_TYPE, HEADER_VALE_URL_ENCODED)
        header(HEADER_NAME_AUTH, HEADER_VALUE_AUTH)
    }
}

private fun createHttpClientAuth(preferences: SharedPreferencesAuth): OkHttpClient {
    return createOkHttpClient {
        val token = preferences.token ?: throw UnauthorizedException()
        header(HEADER_NAME_CONTENT_TYPE, HEADER_VALUE_JSON)
        header(HEADER_NAME_AUTHORIZATION, "$HEADER_VALUE_BEARER $token")
        header(HEADER_NAME_ACCEPT, HEADER_VALUE_JSON)
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