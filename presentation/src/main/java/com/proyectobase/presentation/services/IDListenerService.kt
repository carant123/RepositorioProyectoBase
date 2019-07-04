package com.proyectobase.presentation.services

import android.content.Context
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.proyectobase.presentation.util.Constant

class IDListenerService : FirebaseInstanceIdService() {

    protected var customData: Map<String, String>? = null

    override fun onTokenRefresh() {
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Refreshed token: " + refreshedToken!!)
        saveTokenPref(refreshedToken)
    }

    fun saveTokenPref(token: String?) {
        Log.i(TAG, String.format("Token: %s", token))

        if (!isNullOrEmpty(token)) {
            val preferences = applicationContext.getSharedPreferences(Constant.USER_PREFERENCES, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString(Constant.KEY_TOKEN, token)
            editor.putBoolean(Constant.newTokenFireBase, true)
            editor.apply()
        }
    }

    companion object {

        private val TAG = "IDListenerService"

        fun isNullOrEmpty(value: String?): Boolean {
            if (value == null)
                return true
            else if (value.trim { it <= ' ' } == "")
                return true
            return false
        }
    }
}
