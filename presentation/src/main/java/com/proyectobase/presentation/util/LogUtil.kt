package com.proyectobase.presentation.util

import android.util.Log
import com.crashlytics.android.Crashlytics

object LogUtil {

    @JvmStatic
    fun LOGE(tag: String, message: String?) {
        Crashlytics.log(Log.ERROR, tag, message)
    }

    @JvmStatic
    fun LOGE(tag: String, message: String?, cause: Exception) {
        LOGE(tag, message)
        Crashlytics.logException(cause)
    }

    @JvmStatic
    fun LOGE(tag: String, message: String?, cause: Throwable) {
        LOGE(tag, message)
        Crashlytics.logException(cause)
    }

}
