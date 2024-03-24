package com.example.basecalendar.feature_calendar.domain.use_case.main

import com.example.basecalendar.feature_calendar.domain.model.CalendarDay
import java.util.Calendar

class GetEmptyCalendar {

    operator fun invoke(
        selectedMonth: Int,
        selectedYear: Int
    ): List<CalendarDay> {

        val c = Calendar.getInstance()

        c.set(Calendar.MONTH, selectedMonth)
        c.set(Calendar.YEAR, selectedYear)
        c.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonthInWeek = c.get(Calendar.DAY_OF_WEEK)
        val daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH)

        c.set(Calendar.DAY_OF_MONTH, daysInMonth)
        val lastDayOfMonthInWeek = c.get(Calendar.DAY_OF_WEEK)

        var day = 1

        val listOfDays = mutableListOf<CalendarDay>()

        if (firstDayOfMonthInWeek == 1) {
            for (i in 1..6) {
                listOfDays.add(
                    CalendarDay(
                        listOfEvents = emptyList(),
                        isEmpty = true,
                        dayOfMonth = -1
                    )
                )
            }
            listOfDays.add(
                CalendarDay(
                    listOfEvents = emptyList(),
                    isEmpty = false,
                    dayOfMonth = 1
                )
            )
        }

        while (day < daysInMonth + firstDayOfMonthInWeek - 1) {
            listOfDays.add(
                CalendarDay(
                    listOfEvents = emptyList(),
                    isEmpty = (day < firstDayOfMonthInWeek - 1),
                    dayOfMonth = day - firstDayOfMonthInWeek + 2
                )
            )
            day++
        }
        if (lastDayOfMonthInWeek > 1) {
            for (i in 1..8 - lastDayOfMonthInWeek) {
                listOfDays.add(
                    CalendarDay(
                        listOfEvents = emptyList(),
                        isEmpty = true,
                        dayOfMonth = -1
                    )
                )
            }
        }

        return listOfDays.toList()
    }
}