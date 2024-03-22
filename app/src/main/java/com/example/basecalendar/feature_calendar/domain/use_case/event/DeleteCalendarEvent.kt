package com.example.basecalendar.feature_calendar.domain.use_case.event

import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository

class DeleteCalendarEvent(
    private val repository: CalendarRepository
) {

    suspend operator fun invoke(calendarEventDto: CalendarEventDto) {
        repository.deleteCalendarEvent(calendarEventDto)
    }
}