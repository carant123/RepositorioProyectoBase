package com.proyectobase.data.exception

class DBDataStoreException(cause: Throwable?) :
        Exception(cause?.message, cause)