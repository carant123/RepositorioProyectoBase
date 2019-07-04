package com.proyectobase.presentation.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.proyectobase.presentation.R

fun View.dismissKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun ImageView.loadImageOrGif(ruta: String, error: Int = -1, vieLoading: View) {
    var glide = Glide.with(context)
    if (error != -1) {
        glide = glide.setDefaultRequestOptions(
            RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_button_close))
    }

    glide.load(ruta)
        .thumbnail(0.1f)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                vieLoading.visibility = View.GONE
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                vieLoading.visibility = View.GONE
                return false
            }

        })
        .into(this)
}
