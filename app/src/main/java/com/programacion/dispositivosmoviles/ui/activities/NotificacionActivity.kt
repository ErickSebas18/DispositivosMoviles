package com.programacion.dispositivosmoviles.ui.activities

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.programacion.dispositivosmoviles.R
import com.programacion.dispositivosmoviles.databinding.ActivityNotificacionBinding
import com.programacion.dispositivosmoviles.ui.utilities.BroadcasterNotifications
import java.util.Calendar

class NotificacionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.notificacion.setOnClickListener {
            //createNotificationChannel()
            sendNotificacion()
        }

        binding.notificacionProgramada.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hora = binding.timePicker.hour
            val minutes = binding.timePicker.minute

            Toast.makeText(this, "La notificacion se activará a las : $hora con $minutes", Toast.LENGTH_SHORT).show()
            calendar.set(Calendar.HOUR, hora)
            calendar.set(Calendar.MINUTE, minutes)
            calendar.set(Calendar.SECOND,0)
            sendNotificationTimePicker(calendar.timeInMillis)
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "De una"
            val descriptionText = "Notificaciones variedades"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    val CHANNEL: String = "Notificaciones"

    @SuppressLint("MissingPermission")
    fun sendNotificacion() {
        val notifyIntent = Intent(this, CameraActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val notifyPendingIntent = PendingIntent.getActivity(
            this, 0, notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val noti = NotificationCompat.Builder(this, CHANNEL)
        noti.setContentIntent(notifyPendingIntent)
        noti.setContentTitle("Primera Notificacion")
        noti.setContentText("Tienes una notificacion")
        noti.setSmallIcon(R.drawable.baseline_message_24)
        noti.setPriority(NotificationCompat.PRIORITY_DEFAULT)
        noti.setStyle(
            NotificationCompat.BigTextStyle().bigText("Esta es una notificacion para recordar")
        )

        with(NotificationManagerCompat.from(this)) {
            notify(1, noti.build())
        }
    }

    private fun sendNotificationTimePicker(time: Long) {
        val myIntent = Intent(applicationContext, BroadcasterNotifications::class.java)
        val myPendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            myIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, myPendingIntent)
    }
}