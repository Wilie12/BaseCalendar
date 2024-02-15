package com.example.basecalendar.feature_calendar.util

sealed class Screen(val route: String) {

    object MainScreen: Screen("main_screen")
    object AddEventScreen: Screen("add_event_screen")
}