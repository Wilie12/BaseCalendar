package com.example.basecalendar.feature_calendar.presentation.event_screen

import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto

data class EventState(
    val event: CalendarEventDto = CalendarEventDto(
        id = 0,
        startingDate = 0L,
        endingDate = 0L,
        isTakingWholeDay = false,
        isRepeating = false,
        repeatMode = 0,
        title = "",
        description = "",
        color = 0,
        reminderMode = 0
    ),
    val screenRoute: String = "",
    val isLoading: Boolean = false
)