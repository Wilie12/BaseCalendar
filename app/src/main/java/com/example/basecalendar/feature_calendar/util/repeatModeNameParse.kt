package com.example.basecalendar.feature_calendar.util

import com.example.basecalendar.feature_calendar.data.util.RepeatMode

fun parseRepeatModeIntToString(repeatMode: Int): String {
    return when (repeatMode) {
        RepeatMode.NONE -> "Don't repeat"
        RepeatMode.EVERY_DAY -> "Every day"
        RepeatMode.EVERY_WEEK -> "Every week"
        RepeatMode.EVERY_MONTH -> "Every month"
        RepeatMode.EVERY_YEAR -> "Every year"
        else -> "Something went wrong..."
    }
}