package com.example.basecalendar.feature_calendar.presentation.main_screen

sealed class MainEvent {

    object CurrentMonth: MainEvent()
    object PreviousMonth: MainEvent()
    object NextMonth: MainEvent()
}