package com.example.basecalendar.feature_calendar.util.parsers

import com.example.basecalendar.feature_calendar.data.util.ReminderMode


fun parseReminderModeIntToLongValue(reminderMode: Int): Long {
    return when(reminderMode) {
        ReminderMode.NONE -> 0
        ReminderMode.MINUTES_5 -> 300000
        ReminderMode.MINUTES_10 -> 600000
        ReminderMode.MINUTES_15 -> 900000
        ReminderMode.HOUR_1 -> 3600000
        ReminderMode.DAY_1 -> 86400000
        else -> 0
    }
}