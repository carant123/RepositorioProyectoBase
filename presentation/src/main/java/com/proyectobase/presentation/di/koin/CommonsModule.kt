package com.proyectobase.presentation.di.koin

//import biz.belcorp.ffvv.domain.interactor.impl.rdd.eventos.NotificacionesEventosPresenter
//import biz.belcorp.ffvv.domain.repository.rdd.eventos.AlarmasWorker
//import biz.belcorp.ffvv.presentation.alarms.AlarmsHelper
//import biz.belcorp.ffvv.presentation.alarms.NotificationHelper
//import biz.belcorp.ffvv.presentation.feature.rdd.mi_ruta.eventos.alarmas.AlarmasWorkManager
//import biz.belcorp.ffvv.presentation.feature.rdd.mi_ruta.eventos.sincronizacion.*
//import biz.belcorp.ffvv.presentation.navigation.Navigator
import com.proyectobase.presentation.navigation.Navigator
import org.koin.dsl.module.module


val commonsModule = module {

    factory { Navigator() }

//    factory { AlarmasWorkManager(get()) as AlarmasWorker }
//    factory { AlarmsHelper(get()) }
//
//    factory { NotificationHelper(get()) }
//
//    factory { NotificacionesEventosWorker(get(), get()) as NotificacionesEventosPresenter }
//    factory { NotificacionEventoHelper(get()) as NotificacionEventosHandler }
//    factory { BroadcastEventosHelper(get()) as BroadcastEventosHandler }

}