package com.example.basecalendar.feature_calendar.domain.use_case

import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository

class AddEvent(
    private val repository: CalendarRepository
) {

    suspend operator fun invoke(event: CalendarEventDto) {
        repository.insertCalendarEvent(event)
    }
}