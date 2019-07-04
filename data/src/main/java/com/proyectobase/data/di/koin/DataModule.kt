package com.proyectobase.data.di.koin

import org.koin.dsl.module.module

val dataModule = module {
    dataModules.mapTo(subModules){
        it.invoke(koinContext)
    }
}

private val dataModules by lazy {
    listOf(
        repositoryModule,
        networkModule,
        preferencesModule
    )
}