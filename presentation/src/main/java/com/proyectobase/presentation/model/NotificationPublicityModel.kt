package com.proyectobase.presentation.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class NotificationPublicityModel(modulo: String, imagen: String, urlvideo: String,
                                 colorBotonX: String, colorBotonFondo: String) : Serializable {

    @SerializedName("Modulo")
    val modulo: String? = null

    @SerializedName("Imagen")
    val imagen: String? = null

    @SerializedName("UrlVideo")
    val urlVideo: String? = null

    @SerializedName("ColorBotonX")
    val colorBotonX: String? = null

    @SerializedName("ColorBotonFondo")
    val colorBotonFondo: String? = null

    override fun toString(): String {
        return "{ modulo: $modulo, " +
                "imagen: $imagen, " +
                "urlVideo: $urlVideo, " +
                "colorBotonX: $colorBotonX, " +
                "colorBotonFondo: $colorBotonFondo }"
    }

}