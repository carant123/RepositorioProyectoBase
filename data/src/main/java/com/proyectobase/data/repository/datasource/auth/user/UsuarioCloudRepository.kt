package com.proyectobase.data.repository.datasource.auth.user

import com.proyectobase.data.entity.auth.UsuarioEntity
import com.proyectobase.data.mapper.auth.UsuarioEntityDataMapper
import com.proyectobase.data.net.UsuarioApi
import com.proyectobase.data.net.dto.UsuarioRequest
import com.proyectobase.domain.entity.Usuario
import com.proyectobase.domain.repository.UsuarioRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsuarioCloudRepository
@Inject
constructor(
    private val usuarioApi: UsuarioApi){

    fun obtenerUsuario(codigo: String, password: String): Single<UsuarioEntity> {

        var usuarioRequest = UsuarioRequest()
        usuarioRequest.codigo = codigo
        usuarioRequest.password = password
        //return usuarioApi.obtenerDatos(usuarioRequest)
        return pruebaObtenerusuario()

    }

    fun pruebaObtenerusuario() : Single<UsuarioEntity> {
        val usuarioEntity = UsuarioEntity()
        usuarioEntity.nombre = "Nombre1"
        usuarioEntity.apellido = "Apellido1"
        usuarioEntity.codUsuario = "Codigo1"
        return Single.just(usuarioEntity)
    }
}