package com.proyectobase.presentation.fragment.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import com.proyectobase.presentation.R
import com.proyectobase.presentation.base.BaseDialogFragment
import com.proyectobase.presentation.model.UsuarioModel
import com.proyectobase.presentation.util.dismissKeyboard
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject

class LoginFragment: BaseDialogFragment(), MVPLogin.View {

    private val presenter: LoginPresenter by inject()

    override fun getLayout(): Int {
        return R.layout.fragment_login
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.create(this)
        configurarBotonCerrar()
        configurarBotonAcceder()
        configurarAccionDoneEnPassword()

    }

    private fun configurarBotonCerrar() {
        botonCerrar?.setOnClickListener { dismiss() }
    }

    private fun configurarBotonAcceder() {
        botonAcceder?.setOnClickListener {
            acceder()
        }
    }

    private fun configurarAccionDoneEnPassword() {
        inputPassword?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                acceder()

                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }
    }

    private fun acceder() {
        view?.dismissKeyboard()
        presenter.obtenerUsuario("data","password")

    }

    override fun obtenerUsuario(usuarioModel: UsuarioModel) {
        Log.d("Usuario: ","" + usuarioModel.nombre)
        Log.d("Usuario: ","" + usuarioModel.apellido)
        Log.d("Usuario: ","" + usuarioModel.codUsuario)
    }

    companion object {

        const val ID_RECONOCIMIENTO = "ID_RECONOCIMIENTO"

        // ejemplo
        fun newInstance(idReconocimiento: Long): LoginFragment {
            val bundle = Bundle()
            val fragment = LoginFragment()
            bundle.putLong(ID_RECONOCIMIENTO, idReconocimiento)
            fragment.arguments = bundle
            return fragment
        }
    }

}