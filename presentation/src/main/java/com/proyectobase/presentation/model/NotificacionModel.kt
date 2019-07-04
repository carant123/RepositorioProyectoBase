package com.proyectobase.presentation.model

import java.io.Serializable

/** Created by garyfimo on 5/23/18. */

open class NotificacionModel : Serializable {

    var tipo: Int = 0
    var mensaje: String = ""
    var titulo: String = ""

    override fun toString(): String {
        return "{ tipo: $tipo, " +
                "mensaje: $mensaje, " +
                "titulo: $titulo }"
    }
}