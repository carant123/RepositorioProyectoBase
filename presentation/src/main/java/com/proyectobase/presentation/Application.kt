package com.proyectobase.presentation

import android.annotation.SuppressLint
import android.app.Application
import android.support.v7.app.AppCompatDelegate
import biz.belcorp.ffvv.presentation.di.koin.injectModules
import com.facebook.stetho.Stetho
import com.google.android.gms.tagmanager.DataLayer
//import com.google.android.gms.tagmanager.DataLayer
import com.google.android.gms.tagmanager.TagManager
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowLog
import com.raizlabs.android.dbflow.config.FlowManager

open class MainApplication : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var app: MainApplication

        @SuppressLint("StaticFieldLeak")
        lateinit var tagManager: TagManager

        lateinit var dataLayer: DataLayer

    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        injectModules()
        app = this
        initializeDB()
        initTagManager()
        initStetho()
    }

    protected open fun injectModules() {
        injectModules(this)
    }

    private fun initializeDB() {
        FlowManager
            .init(
                FlowConfig.builder(this)
                    .openDatabasesOnInit(true)
                    .build())

        if (BuildConfig.DEBUG)
            FlowLog.setMinimumLoggingLevel(FlowLog.Level.V)
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            if (!isUnitTesting()) {
                Stetho.initializeWithDefaults(this)
            }
        }
    }

    private fun initTagManager() {
        tagManager = TagManager.getInstance(this)
        tagManager.setVerboseLoggingEnabled(true)
        dataLayer = tagManager.dataLayer
    }

    protected open fun isUnitTesting(): Boolean {
        return false
    }

}