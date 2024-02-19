package com.example.basecalendar.feature_calendar.util

import com.example.basecalendar.R

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object AddEventScreen: Screen("add_event_screen")

    object DayScreen: Screen("day_screen")

}