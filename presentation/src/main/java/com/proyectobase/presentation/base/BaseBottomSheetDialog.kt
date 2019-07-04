package com.proyectobase.presentation.base

import android.content.Context
import android.support.design.widget.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection

open class BaseBottomSheetDialog : BottomSheetDialogFragment() {
    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}