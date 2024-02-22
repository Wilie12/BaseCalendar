package com.example.basecalendar.feature_calendar.util

import java.util.Calendar

fun parseDayOfWeekIntToString(dayOfWeek: Int): String {
    return when (dayOfWeek) {
        Calendar.MONDAY -> "Md"
        Calendar.TUESDAY -> "Tu"
        Calendar.WEDNESDAY -> "Wd"
        Calendar.THURSDAY -> "Th"
        Calendar.FRIDAY -> "Fr"
        Calendar.SATURDAY -> "St"
        Calendar.SUNDAY -> "Sn"
        else -> "Something went wrong..."
    }
}