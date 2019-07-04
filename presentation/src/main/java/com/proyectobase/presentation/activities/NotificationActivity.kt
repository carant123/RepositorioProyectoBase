package com.proyectobase.presentation.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import biz.belcorp.ffvv.presentation.notification.NotificationPublicityCerrable
import biz.belcorp.ffvv.presentation.notification.NotificationListaFragment
import com.proyectobase.presentation.R
import com.proyectobase.presentation.base.BaseActivity
import com.proyectobase.presentation.fragment.login.LoginFragment
import com.proyectobase.presentation.model.NotificacionModel
import com.proyectobase.presentation.model.NotificacionReconocimientoModel
import com.proyectobase.presentation.util.Constant

class NotificationActivity : BaseActivity(), NotificationPublicityCerrable {


    private var notificacion: NotificacionModel? = null
    private var datos :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificacion = intent.getSerializableExtra(Constant.NOTIFICACION_DATOS) as NotificacionModel
        datos = intent.getStringExtra(Constant.NOTIFICACION_DATOS)
        if (datos != null) {
            showFragmentPublicity()
        } else {
            showFragment()
        }
    }

    override val layout: Int
        get() = R.layout.activity_notification

    private fun showFragment() {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        val fragment = getNotificationFragment()
        fragmentTransaction.add(
                R.id.fl_container, fragment, fragment.javaClass.simpleName)

        fragmentTransaction.commit()
    }

    private fun showFragmentPublicity() {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        val fragment = getNotificationPublicityFragment()
        fragmentTransaction.add(
                R.id.fl_container, fragment, fragment.javaClass.simpleName)

        fragmentTransaction.commit()
    }

    private fun getNotificationFragment(): Fragment {
        return when (notificacion) {
            is NotificacionReconocimientoModel ->
                instanciarFragmentReconocimiento(
                        (notificacion as NotificacionReconocimientoModel).idReconocimiento)
            else                               ->
                error("Tipo de notificación no identificada.")
        }
    }

    private fun getNotificationPublicityFragment(): Fragment {
        return instanciarFragmentLista(datos!!)
    }


    private fun instanciarFragmentReconocimiento(idReconocimiento: Long): LoginFragment {

        // el fragmento puede ser otro
        val fragment = LoginFragment.newInstance(idReconocimiento)
        return fragment
    }

    private fun instanciarFragmentLista(datos: String):
            NotificationListaFragment {

        val fragment = NotificationListaFragment.newInstance(datos)
        fragment.establecerListenerCerrable(this)
        return fragment

    }

    override fun alCerrar() {
        finish()
    }

}