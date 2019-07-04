package com.proyectobase.presentation.fragment.login

import com.proyectobase.presentation.model.UsuarioModel

interface MVPLoginSoporte {

    interface View {
        fun obtenerUsuario(usuarioModel: UsuarioModel)
    }
}