package com.example.basecalendar.feature_calendar.util.parsers

import com.example.basecalendar.feature_calendar.data.util.ReminderMode

fun parseReminderModeIntToName(reminderMode: Int): String {
    return when (reminderMode) {
        ReminderMode.NONE -> "Don't reminder"
        ReminderMode.MINUTES_5 -> "5 minutes before"
        ReminderMode.MINUTES_10 -> "10 minutes before"
        ReminderMode.MINUTES_15 -> "15 minutes before"
        ReminderMode.HOUR_1 -> "1 hour before"
        ReminderMode.DAY_1 -> "1 day before"
        else -> "Something went wrong..."
    }
}

fun parseReminderModeIntToNameNotification(reminderMode: Int): String {
    return when (reminderMode) {
        ReminderMode.NONE -> "Don't reminder"
        ReminderMode.MINUTES_5 -> "5 minutes"
        ReminderMode.MINUTES_10 -> "10 minutes"
        ReminderMode.MINUTES_15 -> "15 minutes"
        ReminderMode.HOUR_1 -> "1 hour"
        ReminderMode.DAY_1 -> "1 day"
        else -> "Something went wrong..."
    }
}