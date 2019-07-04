package com.proyectobase.presentation.mapper

import com.proyectobase.domain.entity.Usuario
import com.proyectobase.domain.util.Mapper
import com.proyectobase.presentation.di.PerActivity
import com.proyectobase.presentation.model.UsuarioModel
import javax.inject.Inject

@PerActivity
class UsuarioModelDataMapper @Inject
constructor() : Mapper<Usuario,UsuarioModel>() {

    override fun map(value: Usuario): UsuarioModel {
        val userModel = UsuarioModel()
        userModel.codUsuario = value.codUsuario
        userModel.apellido = value.apellido
        userModel.nombre = value.apellido
        return userModel
    }

    override fun reverseMap(value: UsuarioModel): Usuario {
        val usuario = Usuario()
        usuario.codUsuario = value.codUsuario
        usuario.apellido = value.apellido
        usuario.nombre = value.apellido
        return usuario
    }

}