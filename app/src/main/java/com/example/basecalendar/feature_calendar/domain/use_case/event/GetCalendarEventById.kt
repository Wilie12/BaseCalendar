package com.example.basecalendar.feature_calendar.domain.use_case.event

import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository

class GetCalendarEventById(
    private val repository: CalendarRepository
) {

    suspend operator fun invoke(eventId: Int): CalendarEventDto {
        return repository.getCalendarEventById(eventId)
    }
}