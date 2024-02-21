package com.example.basecalendar.feature_calendar.domain.use_case.main

import com.example.basecalendar.feature_calendar.domain.use_case.GetCurrentDate
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfMonthInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfNextMonthInMillis

data class MainUseCases(
    val getFirstDayOfMonthInMillis: GetFirstDayOfMonthInMillis,
    val getFirstDayOfNextMonthInMillis: GetFirstDayOfNextMonthInMillis,
    val getAllCalendarEventsFromCurrentMonth: GetAllCalendarEventsFromCurrentMonth,
    val getEmptyCalendar: GetEmptyCalendar,
    val getCurrentDate: GetCurrentDate,
    val getCalendarWithEvents: GetCalendarWithEvents
)