package com.proyectobase.presentation.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import com.google.firebase.perf.metrics.Trace
import com.proyectobase.presentation.util.FirebaseUtil
import com.proyectobase.presentation.util.LogUtil.LOGE

abstract class BaseFragment : Fragment() {

    //private var trace: Trace? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initTrace()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //endTrace()
    }

    protected abstract val layout: Int

    private fun initTrace() {
        try {
//            trace = FirebaseUtil.buildMonitoringTrace(this.javaClass.simpleName)
//            trace?.start()
        } catch (exception: Exception) {
            LOGE(javaClass.simpleName, "initTrace", exception)
        }
    }

    private fun endTrace() {
        try {
            //trace?.stop()
        } catch (exception: Exception) {
            LOGE(javaClass.simpleName, "endTrace", exception)
        }
    }

}