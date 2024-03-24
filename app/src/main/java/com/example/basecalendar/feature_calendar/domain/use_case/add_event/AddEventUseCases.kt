package com.example.basecalendar.feature_calendar.domain.use_case.add_event

import com.example.basecalendar.feature_calendar.domain.use_case.GetCalendarEventById

data class AddEventUseCases(
    val addEvent: AddEvent,
    val getSelectedHourAndMinutesInMillis: GetSelectedHourAndMinutesInMillis,
    val getStartOfDay: GetStartOfDay,
    val getCalendarEventById: GetCalendarEventById,
    val updateCalendarEvent: UpdateCalendarEvent
)