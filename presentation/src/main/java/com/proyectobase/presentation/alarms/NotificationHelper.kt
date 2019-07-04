package com.proyectobase.presentation.alarms

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import com.proyectobase.presentation.R
import com.proyectobase.presentation.activities.NotificationActivity
import com.proyectobase.presentation.model.NotificacionModel
import com.proyectobase.presentation.util.Constant
import javax.inject.Inject

class NotificationHelper @Inject constructor(private val context: Context) {

    companion object {

        const val CHANNEL_ID = "10002"
        const val CHANNEL_NAME = "Alerts"
    }

    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun showPublicityNotification(notificacion: NotificacionModel, datos: String) {
        val intent = Intent(context, NotificationActivity::class.java)
        intent.putExtra(Constant.NOTIFICACION, notificacion)
        intent.putExtra(Constant.NOTIFICACION_DATOS, datos)

        val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT)

        val notificationBuilder = NotificationCompat.Builder(context, "")
                .setAutoCancel(true)
                .setChannel(CHANNEL_ID, CHANNEL_NAME, notificationManager)
                //.setSmallIcon(R.drawable.ic_)
                .setContentTitle(notificacion.titulo)
                .setContentText(notificacion.mensaje)
                .setColor(ContextCompat.getColor(context, R.color.purple))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_ALL)

        notificationManager.notify(0, notificationBuilder.build())
    }

    fun cancelAll() {
        notificationManager.cancelAll()
    }
}