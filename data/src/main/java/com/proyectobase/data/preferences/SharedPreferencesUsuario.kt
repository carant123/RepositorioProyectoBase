package com.proyectobase.data.preferences

import android.content.SharedPreferences
import biz.belcorp.ffvv.data.preferences.SharedPreferencesBase

class SharedPreferencesUsuario(shared: SharedPreferences) : SharedPreferencesBase(shared) {

    var nombre: String?
        get() = getString(KEY_NOMBRE)
        set(value) = putString(KEY_NOMBRE, value)

    var apellido: String?
        get() = getString(KEY_APELLIDO)
        set(value) = putString(KEY_APELLIDO, value)

    fun obtenerNombre(): String? = shared.getString(KEY_NOMBRE, null)
    fun obtenerApellido() : String? = shared.getString(KEY_APELLIDO, null)

}