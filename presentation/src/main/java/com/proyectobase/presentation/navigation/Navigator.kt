package com.proyectobase.presentation.navigation

import android.content.Context
import com.proyectobase.presentation.activities.MainActivity
import org.jetbrains.anko.intentFor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

//    fun navigateToLogin(context: Context) {
//        val intentToLaunch = context.intentFor<LoginActivity>()
//        intentToLaunch.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//        context.startActivity(intentToLaunch)
//    }

    fun navigateToHome(context: Context, fromLogin: Boolean) {
        val intentToLaunch = context.intentFor<MainActivity>()
        intentToLaunch.putExtra(NAVIGATE_FROM_LOGIN, fromLogin)
        context.startActivity(intentToLaunch)
    }

    companion object {

        const val NAVIGATE_FROM_LOGIN = "navigate_from_login"

    }

}