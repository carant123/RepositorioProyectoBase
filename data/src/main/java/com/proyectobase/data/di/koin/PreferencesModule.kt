package com.proyectobase.data.di.koin

import android.content.Context
import biz.belcorp.ffvv.data.preferences.SharedPreferencesBase
import biz.belcorp.ffvv.data.preferences.SharedPreferencesUser
import com.proyectobase.data.preferences.SharedPreferencesUsuario
import org.koin.dsl.module.module

val preferencesModule = module {
    single { SharedPreferencesUsuario(get<Context>().usuarioPrefrences) }
}

private val Context.usuarioPrefrences
    get() = getSharedPreferences(SharedPreferencesBase.USUARIO, Context.MODE_PRIVATE)


