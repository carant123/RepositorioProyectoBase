package com.proyectobase.data.net

import com.proyectobase.data.entity.google.PredictionApiResponse
import com.proyectobase.data.entity.ruta.RutaApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GoogleApi {

    @GET
    fun rutaOptima(@Url url: String): Single<RutaApiResponse>

    @GET("maps/api/place/autocomplete/json")
    fun autoCompletePlaces(@Query("key") key: String,
                           @Query("types") types: String,
                           @Query("components", encoded = true) componentes: String,
                           @Query("input") input: String): Single<PredictionApiResponse>

}