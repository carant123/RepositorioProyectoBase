package com.proyectobase.presentation.services

import android.content.Context
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.proyectobase.presentation.util.Constant

class BelcorpIDListenerService : FirebaseInstanceIdService() {
    protected var customData: Map<String, String>? = null
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is also called
     * when the InstanceID token is initially generated, so this is where
     * you retrieve the token.
     */
    // [START refresh_token]
    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Refreshed token: " + refreshedToken!!)
        // TODO: Implement this method to send any registration to your app's servers.
        saveTokenPref(refreshedToken)
    }

    fun saveTokenPref(token: String?) {
        Log.i(TAG, String.format("Token: %s", token))

        if (!isNullOrEmpty(token)) {
            val preferences = applicationContext.getSharedPreferences(Constant.USER_PREFERENCES, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString(Constant.KEY_FCM_TOKEN, token)
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
