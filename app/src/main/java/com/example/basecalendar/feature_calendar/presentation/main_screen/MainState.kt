package com.example.basecalendar.feature_calendar.presentation.main_screen

import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.data.util.CalendarDate
import com.example.basecalendar.feature_calendar.domain.model.CalendarDay

data class MainState(
    val listOfDays: List<CalendarDay> = emptyList(),
    val listOfEvents: List<CalendarEventDto> = emptyList(),
    val listOfEventsFromCurrentMonth: List<CalendarEventDto> = emptyList(),
    val selectedDate: CalendarDate = CalendarDate(
        day = 0,
        month = 0,
        year = 2000
    ),
    val currentDate: CalendarDate = CalendarDate(
        day = 0,
        month = 0,
        year = 2000
    ),
    val isLoading: Boolean = false
)