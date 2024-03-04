package com.example.basecalendar.feature_calendar.domain.use_case.add_event

import java.util.Calendar

class GetSelectedHourAndMinutesInMillis {

    operator fun invoke(
        date: Long,
        hour: Int,
        minutes: Int
    ): Long {

        val c = Calendar.getInstance()

        c.timeInMillis = date
        val day = c.get(Calendar.DAY_OF_MONTH)
        c.clear(Calendar.HOUR)
        c.clear(Calendar.MINUTE)
        c.set(Calendar.DAY_OF_MONTH, day)
        c.set(Calendar.HOUR, hour)
        c.set(Calendar.MINUTE, minutes)

        return c.timeInMillis
    }
}