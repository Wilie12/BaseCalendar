package com.example.basecalendar.feature_calendar.presentation.main_screen

import com.example.basecalendar.feature_calendar.domain.model.CalendarDay

data class TestState(
    val listOfDays: List<CalendarDay> = emptyList(),
    val currentMonth: Int = 0,
    val currentYear: Int = 2000
)
