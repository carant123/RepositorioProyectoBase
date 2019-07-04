package com.proyectobase.data.exception


import java.io.IOException

class ErrorCodeReturnedException(val httpStatusCode: Int) : IOException() {
    var errorCode: String = ""
    var serverMessage: String = ""

    init {
        //LOGE(javaClass.simpleName, "STATUS CODE: $httpStatusCode", this)
    }

    constructor(httpStatusCode: Int, errorCode: String, serverMessage: String) : this(httpStatusCode) {
        this.errorCode = errorCode
        this.serverMessage = serverMessage
        //LOGE(javaClass.simpleName, "$errorCode : $serverMessage", this)
    }

    companion object

}
