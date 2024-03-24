package com.example.basecalendar.feature_calendar.domain.model

import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto

data class CalendarDay(
    val listOfEvents: List<CalendarEventDto>,
    val isEmpty: Boolean,
    val dayOfMonth: Int
)
