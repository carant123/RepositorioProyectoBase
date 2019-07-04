package com.proyectobase.data.mapper.auth

import com.proyectobase.data.entity.auth.UsuarioEntity
import com.proyectobase.domain.entity.Usuario
import com.proyectobase.domain.util.Mapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsuarioEntityDataMapper
@Inject constructor() : Mapper<UsuarioEntity, Usuario>(){

    override fun map(value: UsuarioEntity): Usuario {
        val usuario = Usuario()
        usuario.codUsuario = value.codUsuario
        usuario.nombre = value.nombre
        usuario.apellido = value.apellido
        return usuario
    }

    override fun reverseMap(value: Usuario): UsuarioEntity {
        val usuarioEntity = UsuarioEntity()
        usuarioEntity.codUsuario = value.codUsuario
        usuarioEntity.nombre = value.nombre
        usuarioEntity.apellido = value.apellido
        return usuarioEntity
    }

}