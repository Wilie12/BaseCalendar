package com.example.basecalendar.feature_calendar.data.alarm

data class AlarmItem(
    val id: Long,
    val time: Long,
    val title: String,
    val reminderMode: Int
)
