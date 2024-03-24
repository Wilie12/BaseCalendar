package com.example.basecalendar.feature_calendar.data.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.basecalendar.R
import com.example.basecalendar.feature_calendar.util.navigation.NavigationItem
import com.example.basecalendar.feature_calendar.util.navigation.Screen

object Constants {

    const val uri = "base_calendar://event_screen"

    val red = Color(0xFFF44336).toArgb()
    val pink = Color(0xFFE91E63).toArgb()
    val purple = Color(0xFF673AB7).toArgb()
    val blue = Color(0xFF3F51B5).toArgb()
    val green = Color(0xFF4CAF50).toArgb()
    val yellow = Color(0xFFCDDC39).toArgb()
    val orange = Color(0xFFFF5722).toArgb()

    val listOfColors = listOf(
        red,
        pink,
        purple,
        blue,
        green,
        yellow,
        orange
    )

    val listOfScreens = listOf(
        NavigationItem(
            title = "Day",
            icon = R.drawable.ic_day,
            route = Screen.DayScreen.route
        ),
        NavigationItem(
            title = "Month",
            icon = R.drawable.ic_month,
            route = Screen.MainScreen.route
        )
    )
}