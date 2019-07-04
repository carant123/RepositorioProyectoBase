package biz.belcorp.ffvv.domain.di.koin


import com.proyectobase.domain.interactor.impl.UsuarioUseCase
import org.koin.dsl.module.module

val domainModule = module {

    /* Usuario */
    factory { UsuarioUseCase(get(), get(), get()) }


    /* Sesion */

//    factory { SesionManager(get()) }
//    factory { ObtenerSesionUseCase(get(), get(), get()) }

}