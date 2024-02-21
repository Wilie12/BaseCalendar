package com.example.basecalendar.feature_calendar.presentation.add_event_screen

sealed class AddEventEvent {

    data class EnteredTitle(val value: String) : AddEventEvent()
    data class ChangeIsTakingWholeDay(val value: Boolean) : AddEventEvent()
    data class ChangeStartingDate(val value: Long) : AddEventEvent()
    data class ChangeEndingDate(val value: Long) : AddEventEvent()
    data class ChangeStartingHourAndMinutes(
        val hour: Int,
        val minutes: Int
    ) : AddEventEvent()
    data class ChangeEndingHourAndMinutes(
        val hour: Int,
        val minutes: Int
    ) : AddEventEvent()
    data class ChangeRepeatMode(val value: Int) : AddEventEvent()
    data class ChangeReminderMode(val value: Int) : AddEventEvent()
    data class ChangeColor(val value: Int) : AddEventEvent()
    data class ChangeDescription(val value: String) : AddEventEvent()
    object SaveEvent : AddEventEvent()
}