package com.example.basecalendar.feature_calendar.presentation.main_screen

import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.domain.model.CalendarDay

data class MainState(
    val listOfDays: List<CalendarDay> = emptyList(),
    val listOfEvents: List<CalendarEventDto> = emptyList(),
    val selectedMonth: Int = 0,
    val currentMonth: Int = 0,
    val selectedYear: Int = 2000,
    val currentYear: Int = 2000,
    val currentDay: Int = 1,
    val isLoading: Boolean = false
)