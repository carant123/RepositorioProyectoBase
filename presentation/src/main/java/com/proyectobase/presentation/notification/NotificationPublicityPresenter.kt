package biz.belcorp.ffvv.presentation.notification

import com.google.gson.Gson
import com.proyectobase.presentation.model.NotificationPublicityModel
import com.proyectobase.presentation.util.Constant.DEFAULT_DENSITY
import com.proyectobase.presentation.util.Constant.DELIMITER
import com.proyectobase.presentation.util.Constant.DENSITY_HIGH
import com.proyectobase.presentation.util.Constant.DENSITY_LOW
import com.proyectobase.presentation.util.Constant.DENSITY_MEDIUM
import com.proyectobase.presentation.util.Constant.DENSITY_XHIGH
import com.proyectobase.presentation.util.Constant.DENSITY_XXHIGH
import com.proyectobase.presentation.util.Constant.DENSITY_XXXHIGH
import com.proyectobase.presentation.util.Constant.FILE_EXTENSION
import javax.inject.Inject

class NotificationPublicityPresenter

@Inject
constructor() {

    private var view: NotificationPublicityView? = null
    private var densityDpi: Int = 0


    fun establecerVista(view: NotificationPublicityView, densityDpi: Int) {
        this.view = view
        this.densityDpi = densityDpi
    }

    fun obtainUrlImage(urlImage: String) : String {
        var url: String? = null

        if (urlImage != null)
                if (urlImage.contains(FILE_EXTENSION)) {
                    val urlArray = urlImage.split(DELIMITER).toTypedArray()
                    url = urlArray[0] + DEFAULT_DENSITY + urlArray[1]
                }else if (urlImage.contains(DELIMITER)) {
                    val urlArray = urlImage.split(DELIMITER).toTypedArray()
                    url = urlArray[0] + obtainDensity() + urlArray[1]
                }
        return url!!
    }

    fun obtainDensity() : String {
        var density: String? = DELIMITER
        when (densityDpi) {
            DENSITY_LOW -> density = "ldpi"
            DENSITY_MEDIUM -> density = "mdpi"
            DENSITY_HIGH -> density = "hdpi"
            DENSITY_XHIGH -> density = "xhdpi"
            DENSITY_XXHIGH -> density = "xxhdpi"
            DENSITY_XXXHIGH -> density = "xxxhdpi"
            else -> density = DELIMITER
        }
        return density!!
    }

    fun loadImage(urlImage: String) {
        view?.loadImage(obtainUrlImage(urlImage))
    }

    fun loadVideo(urlVideo: String) {
        view?.loadMovie(urlVideo)
    }

    fun closeButton() {
        view?.closeButton()
    }

    fun changeColorBackground(colorBotonFondo: String) {
        view?.changeColorBackground(colorBotonFondo)
    }

    fun changeColorClose(colorBotonX: String) {
        view?.changeColorClose(colorBotonX)
    }

    fun obtainModelFromString(datos: String?) {
        val gson = Gson()
        var notificationPublicity : NotificationPublicityModel? = null
        if (datos != null)
            notificationPublicity = gson.fromJson(datos, NotificationPublicityModel::class.java)
        view?.loadModel(notificationPublicity)
    }

}