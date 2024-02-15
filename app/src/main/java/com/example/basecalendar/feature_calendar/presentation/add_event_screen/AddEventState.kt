package com.example.basecalendar.feature_calendar.presentation.add_event_screen

data class AddEventState(
    val title: String = "",
    val isTakingWholeDay: Boolean = false,
    val startingDate: Long = 0L,
    val endingDate: Long = 0L,
    val isRepeating: Boolean = false,
    val repeatMode: Int = 0,
    val reminderMode: Int = 0,
    val color: Int = 0,
    val description: String = ""
)