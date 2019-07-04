package com.proyectobase.presentation.presenter

interface Presenter {

    /**
     * Method that control the lifecycle of the view. It should be called in the registrarView's
     * (Activity or Fragment) onResume() method.
     */
    fun resume() = Unit

    /**
     * Method that control the lifecycle of the view. It should be called in the registrarView's
     * (Activity or Fragment) onPause() method.
     */
    fun pause() = Unit

    /**
     * Method that control the lifecycle of the view. It should be called in the registrarView's
     * (Activity or Fragment) onDestroy() method.
     */
    fun destroy() = Unit

}