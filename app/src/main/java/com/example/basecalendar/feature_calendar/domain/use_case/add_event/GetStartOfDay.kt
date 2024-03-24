package com.example.basecalendar.feature_calendar.domain.use_case.add_event

import java.util.Calendar

class GetStartOfDay {

    operator fun invoke(date: Long): Long {
        val c = Calendar.getInstance()

        c.timeInMillis = date
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.clear(Calendar.MINUTE)
        c.clear(Calendar.SECOND)
        c.clear(Calendar.MILLISECOND)

        return c.timeInMillis
    }
}