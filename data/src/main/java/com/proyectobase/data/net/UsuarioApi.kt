package com.proyectobase.data.net

import com.proyectobase.data.entity.auth.UsuarioEntity
import com.proyectobase.data.net.dto.UsuarioRequest
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsuarioApi {

    @GET("FFVVOnlineService/api/usuario/actualizardatos")
    fun obtenerDatos(@Body request: UsuarioRequest): Single<UsuarioEntity>

}