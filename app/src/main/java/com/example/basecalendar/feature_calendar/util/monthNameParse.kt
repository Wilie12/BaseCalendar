package com.example.basecalendar.feature_calendar.util

fun parseMonthIntToString(monthInt: Int): String {
    return when(monthInt) {
        0 -> "January"
        1 -> "February"
        2 -> "March"
        3 -> "April"
        4 -> "May"
        5 -> "June"
        6 -> "July"
        7 -> "August"
        8 -> "September"
        9 -> "October"
        10 -> "November"
        11 -> "December"
        else -> "Something went wrong..."
    }
}