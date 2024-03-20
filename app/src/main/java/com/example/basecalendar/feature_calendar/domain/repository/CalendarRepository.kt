package com.example.basecalendar.feature_calendar.domain.repository

import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto

interface CalendarRepository {

    suspend fun insertCalendarEvent(calendarEventDto: CalendarEventDto)

    suspend fun getAllCalendarEventsFromCurrentMonth(
        firstDayOfMonth: Long,
        firstDayOfNextMonth: Long
    ): List<CalendarEventDto>

    suspend fun getAllCalendarEvents(): List<CalendarEventDto>

    suspend fun getCalendarEventById(eventId: Int): CalendarEventDto
}