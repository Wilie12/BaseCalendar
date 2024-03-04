package com.example.basecalendar.feature_calendar.domain.use_case.main

import com.example.basecalendar.feature_calendar.domain.use_case.GetAllCalendarEvents
import com.example.basecalendar.feature_calendar.domain.use_case.GetCurrentDate
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfMonthInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfNextMonthInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfNextYearInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfYearInMillis

data class MainUseCases(
    val getFirstDayOfMonthInMillis: GetFirstDayOfMonthInMillis,
    val getFirstDayOfNextMonthInMillis: GetFirstDayOfNextMonthInMillis,
    val getEmptyCalendar: GetEmptyCalendar,
    val getCurrentDate: GetCurrentDate,
    val getCalendarWithEvents: GetCalendarWithEvents,
    val getAllCalendarEvents: GetAllCalendarEvents,
    val getFirstDayOfYearInMillis: GetFirstDayOfYearInMillis,
    val getFirstDayOfNextYearInMillis: GetFirstDayOfNextYearInMillis
)