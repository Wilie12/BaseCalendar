package com.example.basecalendar.feature_calendar.data.util

object ReminderMode {

    const val NONE = 0
    const val MINUTES_5 = 1
    const val MINUTES_10 = 2
    const val MINUTES_15 = 3
    const val HOUR_1 = 4
    const val DAY_1 = 5

    val listOfReminderOption = listOf(
        NONE,
        MINUTES_5,
        MINUTES_10,
        MINUTES_15,
        HOUR_1,
        DAY_1
    )
}