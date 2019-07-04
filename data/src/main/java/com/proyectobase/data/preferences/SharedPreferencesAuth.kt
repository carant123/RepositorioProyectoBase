package biz.belcorp.ffvv.data.preferences

import android.content.SharedPreferences

class SharedPreferencesAuth(shared: SharedPreferences) : SharedPreferencesBase(shared) {

    var token: String?
        get() = getString(KEY_TOKEN)
        set(value) = putString(KEY_TOKEN, value)

    var refreshToken: String?
        get() = getString(KEY_REFRESH_TOKEN)
        set(value) = putString(KEY_REFRESH_TOKEN, value)

    fun recuperarToken(): String? = shared.getString(KEY_TOKEN, null)

    fun guardarTokens(token: String?, refreshToken: String?) {
        this.token = token
        this.refreshToken = refreshToken
    }

}