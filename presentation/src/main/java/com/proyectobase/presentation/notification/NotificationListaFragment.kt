package biz.belcorp.ffvv.presentation.notification

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.proyectobase.presentation.R
import com.proyectobase.presentation.base.BaseDialogFragment
import com.proyectobase.presentation.model.NotificationPublicityModel
import com.proyectobase.presentation.util.Constant.NOTIFICACION_DATOS
import com.proyectobase.presentation.util.loadImageOrGif
import kotlinx.android.synthetic.main.fragment_view_notification.*
import javax.inject.Inject


class NotificationListaFragment :
        BaseDialogFragment(),
        NotificationPublicityView {

    override fun getLayout() = R.layout.fragment_view_notification

    @Inject
    lateinit var presenter: NotificationPublicityPresenter
    private var listenerCerrable: NotificationPublicityCerrable? = null
    var notificationPublicityModel: NotificationPublicityModel? = null

    companion object {

        fun newInstance(datos: String): NotificationListaFragment {
            val bundle = Bundle()
            val fragment = NotificationListaFragment()
            bundle.putString(NOTIFICACION_DATOS, datos)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onStart() {
        super.onStart()
        fitFullScreen()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        inicializar()
        presenter.obtainModelFromString(arguments?.getString(NOTIFICACION_DATOS))
    }

    private fun inicializar() {
        var density = requireContext().resources.displayMetrics

        presenter.establecerVista(this, density.densityDpi)
    }

    fun establecerListenerCerrable(listener: NotificationPublicityCerrable) {
        this.listenerCerrable = listener
    }

    override fun loadMovie(urlVideo: String) {
        val uri = Uri.parse(urlVideo)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        listenerCerrable?.alCerrar()
        dismiss()
        //TAGGEO
        startActivity(intent)
    }

    override fun loadImage(urlImage: String) {
        img_notification_background.loadImageOrGif(urlImage, R.drawable.ic_button_close, vieLoading)
    }

    override fun closeButton() {
        listenerCerrable?.alCerrar()
        dismiss()
    }

    override fun changeColorBackground(colorBotonFondo: String) {
        (img_notification_close.background).setColorFilter(Color.parseColor(colorBotonFondo), PorterDuff.Mode.SRC_IN)
    }

    override fun changeColorClose(colorBotonX: String) {
        var colorClose = Color.parseColor(colorBotonX)
        var mMode = PorterDuff.Mode.SRC_ATOP
        val drawable = resources.getDrawable(R.drawable.ic_button_close)
        drawable.setColorFilter(colorClose, mMode)
        img_notification_close.setImageDrawable(drawable)
    }

    override fun loadModel(notificationPublicity: NotificationPublicityModel?) {
        notificationPublicityModel = notificationPublicity
        loadImageAndVideo()
    }

    fun loadImageAndVideo() {
        if (notificationPublicityModel != null) {
            if (notificationPublicityModel!!.imagen != null)
                presenter.loadImage(notificationPublicityModel!!.imagen!!)

            rl_button_close?.setOnClickListener { presenter.closeButton() }

            if (notificationPublicityModel!!.urlVideo != null)
                img_notification_background?.setOnClickListener { presenter.loadVideo(notificationPublicityModel!!.urlVideo!!) }

            if (notificationPublicityModel!!.colorBotonFondo != null)
                presenter.changeColorBackground(notificationPublicityModel!!.colorBotonFondo!!)

            if (notificationPublicityModel!!.colorBotonX != null)
                presenter.changeColorClose(notificationPublicityModel!!.colorBotonX!!)
        }
    }

    fun showLoading() {
        vieLoading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        vieLoading.visibility = View.GONE
    }

}