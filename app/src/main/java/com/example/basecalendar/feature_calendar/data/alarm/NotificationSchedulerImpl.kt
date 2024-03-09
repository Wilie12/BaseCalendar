package com.example.basecalendar.feature_calendar.data.alarm

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.basecalendar.R
import com.example.basecalendar.feature_calendar.util.parsers.parseReminderModeIntToNameNotification

class NotificationSchedulerImpl(
    private val context: Context
): NotificationScheduler {

    // TODO - design notifications

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun showNotification(item: AlarmItem) {
        val notification = NotificationCompat.Builder(context, "NOTIFICATION_CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Upcoming event")
            .setContentTitle("${item.title} will start in ${parseReminderModeIntToNameNotification(item.reminderMode)}")
            .build()

        notificationManager.notify(
            item.time.toInt(),
            notification
        )
    }
}