package biz.belcorp.ffvv.presentation.di.koin

import android.app.Activity
import android.support.v4.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import org.koin.standalone.inject

inline fun <reified T : Any> Activity.injectActivity(): Lazy<T> =
        inject(parameters = { parametersOf(this) })

inline fun <reified T : Any> Fragment.injectFragment(): Lazy<T> =
        inject(parameters = { parametersOf(this) })

inline fun <reified T : Any> KoinComponent.inject(vararg params: Any?): Lazy<T> =
        inject(parameters = { parametersOf(*params) })

inline fun <reified T : Any> KoinComponent.get(vararg params: Any?): T =
        get(parameters = { parametersOf(*params) })
