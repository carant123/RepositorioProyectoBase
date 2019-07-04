package com.proyectobase.data.di.koin


import com.proyectobase.data.di.koin.submodules.usuarioModule
import org.koin.dsl.module.module

val repositoryModule = module {

    repositoryModules.mapTo(subModules) {
        it.invoke(koinContext)
    }

}

private val repositoryModules by lazy {
    listOf(
            usuarioModule
    )
}