package com.proyectobase.data.di.koin.submodules

import com.proyectobase.data.mapper.auth.UsuarioEntityDataMapper
import com.proyectobase.data.repository.datasource.auth.user.UsuarioCloudRepository
import com.proyectobase.data.repository.datasource.auth.user.UsuarioDataRepository
import com.proyectobase.domain.repository.UsuarioRepository
import org.koin.dsl.module.module


val usuarioModule = module {

    // Mappers
    factory { UsuarioEntityDataMapper() }

    // DataSources
    factory { UsuarioCloudRepository(get()) }

    // Repositories
    factory { UsuarioDataRepository(get(), get()) as UsuarioRepository }

}