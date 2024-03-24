package com.example.basecalendar.feature_calendar.domain.use_case.event

import com.example.basecalendar.feature_calendar.domain.use_case.GetCalendarEventById

data class EventUseCases(
    val getCalendarEventById: GetCalendarEventById,
    val deleteCalendarEvent: DeleteCalendarEvent
)