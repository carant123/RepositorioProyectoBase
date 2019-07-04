package com.proyectobase.presentation.di.koin

import com.proyectobase.presentation.fragment.login.LoginPresenter
import com.proyectobase.presentation.mapper.UsuarioModelDataMapper
import org.koin.dsl.module.module

val loginSoporteModule = module {

    factory { UsuarioModelDataMapper() }
    factory { LoginPresenter(get(), get()) }

}