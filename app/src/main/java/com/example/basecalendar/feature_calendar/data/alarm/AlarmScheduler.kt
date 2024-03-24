package com.example.basecalendar.feature_calendar.data.alarm

interface AlarmScheduler {

    fun schedule(item: AlarmItem)
    fun cancel(item: AlarmItem)
}