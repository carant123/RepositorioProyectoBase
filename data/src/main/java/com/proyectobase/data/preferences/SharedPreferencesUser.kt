package biz.belcorp.ffvv.data.preferences

import android.content.SharedPreferences

class SharedPreferencesUser(shared: SharedPreferences) : SharedPreferencesBase(shared) {

    var rol: String?
        get() = getString(KEY_ROL)
        set(value) = putString(KEY_ROL, value)

    var nombre: String?
        get() = getString(KEY_FIRST_NAME)
        set(value) = putString(KEY_FIRST_NAME, value)

    var apellido: String?
        get() = getString(KEY_LAST_NAME)
        set(value) = putString(KEY_LAST_NAME, value)

    var apellidoMaterno: String?
        get() = getString(KEY_SECOND_NAME)
        set(value) = putString(KEY_SECOND_NAME, value)

    var documento: String?
        get() = getString(KEY_DOCUMENTO)
        set(value) = putString(KEY_DOCUMENTO, value)

    var codConsultora: String?
        get() = getString(KEY_COD_CONSULTORA)
        set(value) = putString(KEY_COD_CONSULTORA, value)

    var username: String?
        get() = getString(KEY_USERNAME)
        set(value) = putString(KEY_USERNAME, value)

    var password: String?
        get() = getString(KEY_PASSWORD)
        set(value) = putString(KEY_PASSWORD, value)

    var latitud: String?
        get() = getString(KEY_LATITUD)
        set(value) = putString(KEY_LATITUD, value)

    var longitud: String?
        get() = getString(KEY_LONGITUD)
        set(value) = putString(KEY_LONGITUD, value)

    var fcmToken: String?
        get() = getString(KEY_FCM_TOKEN)
        set(value) = putString(KEY_FCM_TOKEN, value)

    var isNewFcmToken: Boolean
        get() = getBoolean(KEY_NEW_FCM_TOKEN)
        set(value) = putBoolean(KEY_NEW_FCM_TOKEN, value)

    var validUrl: String?
        get() = getString(KEY_VALID_URL)
        set(value) = putString(KEY_VALID_URL, value)


    fun recuperarUsername(): String? = shared.getString(KEY_USERNAME, null)




}