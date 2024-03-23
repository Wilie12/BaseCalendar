package com.example.basecalendar.feature_calendar.data.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver: BroadcastReceiver() {

    @Inject
    lateinit var notificationScheduler: NotificationScheduler

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        val time = intent?.getLongExtra("EXTRA_TIME", 0) ?: return
        val reminderMode = intent?.getIntExtra("EXTRA_REMINDER_MODE", 0) ?: return
        val id = intent?.getLongExtra("EXTRA_ID", 0) ?: return
        println("ALARM_RECEIVED: $message")
        if (System.currentTimeMillis() < time) {
            notificationScheduler.showNotification(
                AlarmItem(
                    id = id,
                    time = time,
                    title = message,
                    reminderMode = reminderMode
                )
            )
        }
    }
}