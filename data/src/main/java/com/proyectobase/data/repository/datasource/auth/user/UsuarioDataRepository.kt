package com.proyectobase.data.repository.datasource.auth.user

import com.proyectobase.data.mapper.auth.UsuarioEntityDataMapper
import com.proyectobase.data.net.dto.UsuarioRequest
import com.proyectobase.domain.entity.Usuario
import com.proyectobase.domain.repository.UsuarioRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsuarioDataRepository
@Inject
constructor(
    private val cloudStore: UsuarioCloudRepository,
    private val usuarioEntityDataMapper: UsuarioEntityDataMapper) : UsuarioRepository {


    override fun obtenerUsuario(codigo: String, password: String): Single<Usuario> {
        return cloudStore.obtenerUsuario(codigo,password).map { usuarioEntityDataMapper.map(it) }
    }


}