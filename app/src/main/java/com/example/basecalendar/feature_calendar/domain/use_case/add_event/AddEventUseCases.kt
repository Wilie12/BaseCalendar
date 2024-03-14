package com.example.basecalendar.feature_calendar.domain.use_case.add_event

data class AddEventUseCases(
    val addEvent: AddEvent,
    val getSelectedHourAndMinutesInMillis: GetSelectedHourAndMinutesInMillis,
    val getStartOfDay: GetStartOfDay
)