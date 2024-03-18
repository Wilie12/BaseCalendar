package com.example.basecalendar.feature_calendar.util.parsers

import com.example.basecalendar.feature_calendar.data.util.RepeatMode

fun parseRepeatModeIntToLongValue(repeatMode: Int): Long {
    return when(repeatMode) {
        RepeatMode.NONE -> 0
        RepeatMode.EVERY_DAY -> 86400000
        RepeatMode.EVERY_WEEK -> 604800000
        RepeatMode.EVERY_MONTH -> 2628000000
        RepeatMode.EVERY_YEAR -> 31536000000
        else -> 0
    }
}