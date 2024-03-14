package com.example.basecalendar

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import dagger.hilt.android.HiltAndroidApp


const val NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ID"

@HiltAndroidApp
class BaseCalendarApp: Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel  = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Event reminder",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Used for reminding upcoming events"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}