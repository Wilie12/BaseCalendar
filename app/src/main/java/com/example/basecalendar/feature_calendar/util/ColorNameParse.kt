package com.example.basecalendar.feature_calendar.util

import com.example.basecalendar.feature_calendar.data.util.Constants

fun parseColorIntToString(color: Int): String {
    return when(color) {
        Constants.red -> "Red"
        Constants.pink -> "Pink"
        Constants.purple -> "Purple"
        Constants.blue -> "Blue"
        Constants.green -> "Green"
        Constants.yellow -> "Yellow"
        Constants.orange -> "Orange"
        else -> "Something went wrong..."
    }
}