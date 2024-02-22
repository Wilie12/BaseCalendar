package com.example.basecalendar.feature_calendar.presentation.day_screen

import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.data.util.CalendarDate

data class DayState(
    val listOfEvents: List<CalendarEventDto> = emptyList(),
    val selectedDate: CalendarDate = CalendarDate(
        day = 1,
        month = 0,
        year = 2000
    ),
    val currentDate: CalendarDate = CalendarDate(
        day = 1,
        month = 0,
        year = 2000
    ),
    val dayOfWeek: Int = 1,
    val isLoading: Boolean = false
)