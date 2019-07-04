package com.proyectobase.domain.repository

import com.proyectobase.domain.entity.Usuario
import io.reactivex.Single

interface UsuarioRepository {

    fun obtenerUsuario(codigo: String, password: String): Single<Usuario>

}