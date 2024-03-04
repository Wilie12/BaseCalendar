package com.example.basecalendar.feature_calendar.util.navigation

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object AddEventScreen: Screen("add_event_screen")

    object DayScreen: Screen("day_screen")

}