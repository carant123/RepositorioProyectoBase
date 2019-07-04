package com.proyectobase.domain.interactor

import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable

open class BaseCompletableObserver : CompletableObserver {

    override fun onComplete() {
        // no-op by default.
    }

    override fun onError(e: Throwable) {
        // no-op by default.
    }

    override fun onSubscribe(d: Disposable) {
        // no-op by default.
    }
}