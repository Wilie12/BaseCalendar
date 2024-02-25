package com.example.basecalendar.feature_calendar.presentation.day_screen

sealed class DayEvent {
    // TODO
    object PreviousDay: DayEvent()
    object CurrentDay: DayEvent()
    object NextDay: DayEvent()
}