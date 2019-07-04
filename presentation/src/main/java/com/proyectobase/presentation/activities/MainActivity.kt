package com.proyectobase.presentation.activities

import android.os.Bundle
import com.proyectobase.presentation.R
import com.proyectobase.presentation.base.BaseActivity
import com.proyectobase.presentation.fragment.login.LoginSoporteFragment

class MainActivity: BaseActivity() {


    override val layout: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(R.id.fragmentContainer, LoginSoporteFragment())
    }
}