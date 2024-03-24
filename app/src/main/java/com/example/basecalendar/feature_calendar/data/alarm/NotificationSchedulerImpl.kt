package com.example.basecalendar.feature_calendar.data.alarm

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.example.basecalendar.NOTIFICATION_CHANNEL_ID
import com.example.basecalendar.R
import com.example.basecalendar.feature_calendar.presentation.MainActivity
import com.example.basecalendar.feature_calendar.util.parsers.parseReminderModeIntToNameNotification

class NotificationSchedulerImpl(
    private val context: Context
): NotificationScheduler {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun showNotification(item: AlarmItem) {

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("base_calendar://event_screen/${item.id}/main_screen"),
            context,
            MainActivity::class.java
        )
        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Upcoming event")
            .setContentText("${item.title} will start in ${parseReminderModeIntToNameNotification(item.reminderMode)}")
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(
            item.time.toInt(),
            notification
        )
    }
}