package com.programacion.dispositivosmoviles.ui.utilities

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.programacion.dispositivosmoviles.R
import com.programacion.dispositivosmoviles.ui.activities.CameraActivity

class BroadcasterNotifications : BroadcastReceiver() {

    val CHANNEL: String = "Notificaciones"

    override fun onReceive(context: Context, intent: Intent) {

        val notifyIntent = Intent(context, CameraActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val notifyPendingIntent = PendingIntent.getActivity(
            context, 0, notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val noti = NotificationCompat.Builder(context, CHANNEL)
        noti.setContentIntent(notifyPendingIntent)
        noti.setContentTitle("Primera Notificacion")
        noti.setContentText("Tienes una notificacion")
        noti.setSmallIcon(R.drawable.baseline_message_24)
        noti.setPriority(NotificationCompat.PRIORITY_DEFAULT)
        noti.setStyle(
            NotificationCompat.BigTextStyle().bigText("Esta es una notificacion para recordar")
        )

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, noti.build())
    }


}