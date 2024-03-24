package com.example.basecalendar.feature_calendar.domain.use_case.main

import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.domain.model.CalendarDay
import java.util.Calendar

class GetCalendarWithEvents {

    operator fun invoke(
        listOfDays: List<CalendarDay>,
        listOfEvents: List<CalendarEventDto>
    ): List<CalendarDay> {

        val c = Calendar.getInstance()

        val listOfDays = listOfDays.toMutableList()

        listOfEvents.forEach { calendarEvent ->

            c.timeInMillis = calendarEvent.startingDate
            val startingDay = c.get(Calendar.DAY_OF_MONTH)

            c.timeInMillis = calendarEvent.endingDate
            val endingDay = c.get(Calendar.DAY_OF_MONTH)


            for (i in startingDay..endingDay) {
                val listOfEventsFromCurrentDay =
                    listOfDays[listOfDays.indexOf(listOfDays.find { calendarDay ->
                        calendarDay.dayOfMonth == i
                    })].listOfEvents.toMutableList()
                listOfEventsFromCurrentDay += calendarEvent
                listOfDays[
                    listOfDays.indexOf(listOfDays.find { calendarDay ->
                        calendarDay.dayOfMonth == i
                    })] = CalendarDay(
                    listOfEvents = listOfEventsFromCurrentDay.sortedBy { it.startingDate },
                    isEmpty = false,
                    dayOfMonth = i
                )
            }
        }

        return listOfDays.toList()
    }
}