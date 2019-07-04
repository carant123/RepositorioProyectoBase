package com.proyectobase.data.exception

import android.util.Log
import com.proyectobase.data.exception.ErrorCodeReturnedException

import org.json.JSONObject

class ErrorCodeReturnedExceptionExtractor {

    fun parse(statusCode: Int, json: String): ErrorCodeReturnedException {
        try {
            Log.e("json resultado", "eror: $json")
            val obj = JSONObject(json)

            when {
                obj.has("error") -> {
                    val error = obj.getString("error")
                    val message = obj.getString("error_description")
                    return ErrorCodeReturnedException(statusCode, error, message)
                }
                obj.has("resultado") -> {
                    val jsonResultado = obj.getJSONObject("resultado")
                    val message: String
                    val error: String
                    if (jsonResultado.has("message")) {
                        error = ""
                        message = jsonResultado.getString("message")
                    } else {
                        error = jsonResultado.getString("error")
                        message = jsonResultado.getString("error_description")
                    }
                    return ErrorCodeReturnedException(statusCode, error, message)
                }
                else -> {
                    val error = obj.getString("error")
                    val message = obj.getString("error_description")
                    return ErrorCodeReturnedException(statusCode, error, message)
                }
            }

        } catch (e: Exception) {
            return ErrorCodeReturnedException(statusCode, "", "El app no puede procesar su solicitud.")
        }

    }
}
