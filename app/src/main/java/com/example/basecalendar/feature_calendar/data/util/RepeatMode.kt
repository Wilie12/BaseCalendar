package com.example.basecalendar.feature_calendar.data.util

object RepeatMode {

    const val NONE = 0
    const val EVERY_DAY = 1
    const val EVERY_WEEK = 2
    const val EVERY_MONTH = 3
    const val EVERY_YEAR = 4

    val listOfRepeatOptions = listOf(
        NONE,
        EVERY_DAY,
        EVERY_WEEK,
        EVERY_MONTH,
        EVERY_YEAR
    )
}