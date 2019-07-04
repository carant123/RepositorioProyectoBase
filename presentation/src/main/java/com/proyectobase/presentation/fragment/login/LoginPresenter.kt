package com.proyectobase.presentation.fragment.login

import android.util.Log
import com.proyectobase.domain.entity.Usuario
import com.proyectobase.domain.interactor.BaseSingleObserver
import com.proyectobase.domain.interactor.impl.UsuarioUseCase
import com.proyectobase.presentation.mapper.UsuarioModelDataMapper
import com.proyectobase.presentation.presenter.Presenter

class LoginPresenter(
            private val usuarioUseCase: UsuarioUseCase,
            private val usuarioModelDataMapper: UsuarioModelDataMapper) : Presenter{

    private var view: MVPLogin.View ?= null

    fun create(view: MVPLogin.View) {
        this.view = view
    }

    fun obtenerUsuario(codigo: String, password: String) {
        usuarioUseCase.obtenerUsuario(codigo, password, baseSingleObserver())
    }


    private inner class baseSingleObserver : BaseSingleObserver<Usuario>() {

        override fun onSuccess(t: Usuario) {
            val usuarioModel = usuarioModelDataMapper.map(t)
            view?.obtenerUsuario(usuarioModel)
        }

        override fun onError(e: Throwable) {
            Log.d("Error: ", "" + e.localizedMessage)
        }

    }

}