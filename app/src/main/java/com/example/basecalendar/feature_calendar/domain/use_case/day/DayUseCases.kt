package com.example.basecalendar.feature_calendar.domain.use_case.day

import com.example.basecalendar.feature_calendar.domain.use_case.GetCurrentDate
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfMonthInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfNextMonthInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.main.GetAllCalendarEventsFromCurrentMonth

data class DayUseCases(
    val getAllCalendarEventsFromCurrentMonth: GetAllCalendarEventsFromCurrentMonth,
    val getFirstDayOfMonthInMillis: GetFirstDayOfMonthInMillis,
    val getFirstDayOfNextMonthInMillis: GetFirstDayOfNextMonthInMillis,
    val getCurrentDate: GetCurrentDate
)