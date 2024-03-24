package com.example.basecalendar.feature_calendar.domain.use_case

import java.util.Calendar

class GetFirstDayOfNextYearInMillis {

    operator fun invoke(
        currentYear: Int
    ): Long {

        val c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MONTH, Calendar.JANUARY)
        c.set(Calendar.YEAR, currentYear + 1)
        c.clear(Calendar.MINUTE)
        c.clear(Calendar.SECOND)
        c.clear(Calendar.MILLISECOND)

        c.set(Calendar.DAY_OF_MONTH, 1)

        return c.timeInMillis
    }
}