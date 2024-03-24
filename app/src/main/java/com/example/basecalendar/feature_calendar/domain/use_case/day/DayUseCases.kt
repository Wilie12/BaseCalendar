package com.example.basecalendar.feature_calendar.domain.use_case.day

import com.example.basecalendar.feature_calendar.domain.use_case.GetAllCalendarEventsFromCurrentYear
import com.example.basecalendar.feature_calendar.domain.use_case.GetCurrentDate
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfMonthInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfNextMonthInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfNextYearInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfYearInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.main.GetCalendarWithEvents
import com.example.basecalendar.feature_calendar.domain.use_case.main.GetEmptyCalendar

data class DayUseCases(
    val getFirstDayOfMonthInMillis: GetFirstDayOfMonthInMillis,
    val getFirstDayOfNextMonthInMillis: GetFirstDayOfNextMonthInMillis,
    val getCurrentDate: GetCurrentDate,
    val getEmptyCalendar: GetEmptyCalendar,
    val getCalendarWithEvents: GetCalendarWithEvents,
    val getAllCalendarEventsFromCurrentYear: GetAllCalendarEventsFromCurrentYear,
    val getFirstDayOfYearInMillis: GetFirstDayOfYearInMillis,
    val getFirstDayOfNextYearInMillis: GetFirstDayOfNextYearInMillis
)