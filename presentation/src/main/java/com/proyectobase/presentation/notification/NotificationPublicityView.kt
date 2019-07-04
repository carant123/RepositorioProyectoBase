package biz.belcorp.ffvv.presentation.notification

import com.proyectobase.presentation.model.NotificationPublicityModel

interface NotificationPublicityView {

    fun loadMovie(urlVideo: String)

    fun loadImage(urlImage: String)

    fun closeButton()

    fun changeColorBackground(colorBotonFondo: String)

    fun changeColorClose(colorBotonX: String)

    fun loadModel(notificationPublicity: NotificationPublicityModel?)
}