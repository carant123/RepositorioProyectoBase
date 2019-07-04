package com.proyectobase.presentation.di.koin

import com.proyectobase.presentation.fragment.login.LoginSoportePresenter
import com.proyectobase.presentation.mapper.UsuarioModelDataMapper
import com.proyectobase.presentation.navigation.Navigator
import org.koin.dsl.module.module

val loginSoporteModule = module {

    factory { UsuarioModelDataMapper() }
    factory { LoginSoportePresenter(get(), get()) }

}