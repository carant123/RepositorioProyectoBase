package biz.belcorp.ffvv.data.preferences

import android.content.SharedPreferences

abstract class SharedPreferencesBase
    (val shared: SharedPreferences) {

    companion object {

        // shareferences necesarios

        const val AUTHENTICATION = "AUTHETICATION"
        const val USER = "UserPreferences"
        const val USUARIO = "UsuarioPreferences"

        const val KEY_TOKEN = "token"
        const val KEY_REFRESH_TOKEN = "refreshToken"
        const val KEY_ROL = "rol"

        const val KEY_FIRST_NAME = "first_name"
        const val KEY_LAST_NAME = "last_name"
        const val KEY_SECOND_NAME = "second_name"
        const val KEY_DOCUMENTO = "documento_identidad"
        const val KEY_COD_CONSULTORA = "codConsultora"
        const val KEY_USERNAME = "username"

        const val KEY_PASSWORD = "password"
        const val KEY_LATITUD = "latitud"
        const val KEY_LONGITUD = "longitud"
        const val KEY_FCM_TOKEN = "KEY_FCM_TOKEN"
        const val KEY_NEW_FCM_TOKEN = "new_token_fire_base"
        const val KEY_VALID_URL = "valid_url"

        const val KEY_NOMBRE = "nombre"
        const val KEY_APELLIDO = "apellido"


    }

    fun editor(): SharedPreferences.Editor {
        return shared.edit()
    }

    fun getString(key: String): String? {
        return shared.getString(key, null)
    }

    fun putString(key: String, value: String?) {
        editor().putString(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return shared.getBoolean(key, false)
    }

    fun putBoolean(key: String, value: Boolean) {
        editor().putBoolean(key, value).apply()
    }

    fun getLong(key: String): Long {
        return shared.getLong(key, 0)
    }

    fun putLong(key: String, value: Long) {
        editor().putLong(key, value).apply()
    }

}