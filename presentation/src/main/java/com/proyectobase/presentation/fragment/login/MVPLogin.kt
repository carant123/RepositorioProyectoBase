package com.proyectobase.presentation.fragment.login

import com.proyectobase.presentation.model.UsuarioModel

interface MVPLogin {

    interface View {
        fun obtenerUsuario(usuarioModel: UsuarioModel)
    }
}