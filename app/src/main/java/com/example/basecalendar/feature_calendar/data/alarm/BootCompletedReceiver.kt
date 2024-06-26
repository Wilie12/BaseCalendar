package com.example.basecalendar.feature_calendar.data.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootCompletedReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            context?.let {
                it.startService(Intent(it, AlarmService::class.java))
            }
        }
    }
}