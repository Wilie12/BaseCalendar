package com.example.basecalendar.feature_calendar.presentation.event_screen

import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.data.util.Constants
import com.example.basecalendar.feature_calendar.data.util.ReminderMode
import com.example.basecalendar.feature_calendar.data.util.RepeatMode

data class EventState(
    val event: CalendarEventDto = CalendarEventDto(
        id = 0,
        startingDate = 1691835780000,
        endingDate = 1691842980000L,
        isTakingWholeDay = false,
        isRepeating = false,
        repeatMode = RepeatMode.EVERY_WEEK,
        title = "Title",
        description = "Description",
        color = Constants.blue,
        reminderMode = ReminderMode.HOUR_1
    ),
    val screenRoute: String = "",
)