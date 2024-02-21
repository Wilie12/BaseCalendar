package com.example.basecalendar.feature_calendar.domain.use_case

import com.example.basecalendar.feature_calendar.data.util.CalendarDate
import java.util.Calendar

class GetCurrentDate {

    operator fun invoke(): CalendarDate {
        val c = Calendar.getInstance()
        val currentMonth = c.get(Calendar.MONTH)
        val currentYear = c.get(Calendar.YEAR)
        val currentDay = c.get(Calendar.DAY_OF_MONTH)

        return CalendarDate(
            day = currentDay,
            month = currentMonth,
            year = currentYear
        )
    }
}