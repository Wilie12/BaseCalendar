package com.example.basecalendar.feature_calendar.domain.use_case.alarm_service

import com.example.basecalendar.feature_calendar.domain.use_case.GetAllCalendarEventsFromCurrentYear
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfNextYearInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfYearInMillis

data class AlarmServiceUseCases(
    val getAllCalendarEventsFromCurrentYear: GetAllCalendarEventsFromCurrentYear,
    val getFirstDayOfYearInMillis: GetFirstDayOfYearInMillis,
    val getFirstDayOfNextYearInMillis: GetFirstDayOfNextYearInMillis
)
