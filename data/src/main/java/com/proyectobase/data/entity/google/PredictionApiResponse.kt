package com.proyectobase.data.entity.google

import com.google.gson.annotations.SerializedName

class PredictionApiResponse {

    @SerializedName("predictions")
    val predictions: List<Prediction> = emptyList()

    class Prediction {
        @SerializedName("description")
        val description: String = ""
    }

}