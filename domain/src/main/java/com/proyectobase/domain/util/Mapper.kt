package com.proyectobase.domain.util


abstract class Mapper<T1, T2> {

    abstract fun map(value: T1): T2
    abstract fun reverseMap(value: T2): T1

    fun map(values: List<T1>): List<T2> {
        return values.map { map(it) }
    }

    fun reverseMap(values: List<T2>): List<T1> {
        return values.map { reverseMap(it) }
    }

}
