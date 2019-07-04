package com.proyectobase.presentation.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.proyectobase.domain.interactor.BaseSingleObserver
import com.proyectobase.presentation.alarms.NotificationHelper
import com.proyectobase.presentation.model.NotificacionModel
import com.proyectobase.presentation.util.Constant
import com.proyectobase.presentation.util.Constant.TYPE_PUSH_GESTOR_CONTENIDO
import dagger.Lazy
import dagger.android.AndroidInjection
import javax.inject.Inject

class BelcorpFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationHelper: NotificationHelper

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        try {

            AndroidInjection.inject(this)

//            if (noEsSesionActiva())
//                return

            val data = remoteMessage.data

            if (data.isNotEmpty()) {

                val datos = data[DATA].toString()
                val titulo = data[TITULO].toString()
                val mensaje = data[MENSAJE].toString()
                val modulo = datos.split(DELIMITADOR).elementAtOrNull(1) ?: ""
                val valoresModulo = datos.split(DELIMITADOR)

                decidirConModulo(modulo, datos, titulo, mensaje, valoresModulo)

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //private fun noEsSesionActiva() = !obtenerSesionUseCase.esSesionActiva()

    private fun decidirConModulo(modulo: String,
                                 datos: String,
                                 titulo: String,
                                 mensaje: String, valoresModulo: List<String>) {

        if (modulo.contains(TYPE_PUSH_GESTOR_CONTENIDO)) {
            procesarMensajePublicity(modulo, titulo, mensaje)

        }

    }

    private fun procesarMensajePublicity(datos: String, titulo: String, mensaje: String) {
        val notificacion = crearModeloPublicity(titulo, mensaje)
        showNotificacionPushPublicity(notificacion, datos)
    }

    private fun crearModeloPublicity(titulo: String,
                                     mensaje: String): NotificacionModel {
        val notificacion = NotificacionModel()

        notificacion.tipo = 1
        notificacion.titulo = titulo
        notificacion.mensaje = mensaje

        return notificacion
    }

    private fun showNotificacionPushPublicity(notificacion: NotificacionModel, datos: String) {
        notificationHelper.showPublicityNotification(notificacion, datos)
    }

    companion object {

        const val KEY_DATA = "KEY_DATA"
        const val TYPE_NOTIFICACION = "TYPE_NOTIFICACION"

        const val DATA = "data"
        const val TITULO = "titulo"
        const val MENSAJE = "mensaje"
        const val DELIMITADOR = "|"

    }
}
