package com.proyectobase.presentation.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.proyectobase.presentation.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }

    protected abstract val layout: Int

    protected fun addFragment(containerViewId: Int, fragment: Fragment,
                              anim: Boolean = false, toBackStack: Boolean = false) {
        supportFragmentManager?.beginTransaction()?.apply {
            if (anim) setCustomAnimations(R.anim.enter, R.anim.exit)
            add(containerViewId, fragment, fragment.javaClass.name)
            if (toBackStack) addToBackStack(fragment.javaClass.name)
        }?.commit()
    }

    protected open fun emptyStackFragment() {
        supportFragmentManager?.let {
            for (i in 0 until it.backStackEntryCount) {
                it.popBackStack()
            }
        }
    }

}