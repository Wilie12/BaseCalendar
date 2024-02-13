package com.example.basecalendar.feature_calendar.domain.use_case.main

import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository

class GetAllCalendarEventsFromCurrentMonth(
    private val repository: CalendarRepository
) {

    suspend operator fun invoke(
        firstDayOfMonth: Long,
        firstDayOfNextMonth: Long
    ): List<CalendarEventDto> {
        return repository.getAllCalendarEventsFromCurrentMonth(
            firstDayOfMonth = firstDayOfMonth,
            firstDayOfNextMonth = firstDayOfNextMonth
        )
    }
}