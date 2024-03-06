package com.example.basecalendar.feature_calendar.data.repository

import com.example.basecalendar.feature_calendar.data.local_data_source.CalendarDao
import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository
import javax.inject.Inject

class CalendarRepositoryImpl(
    private val calendarDao: CalendarDao
): CalendarRepository {

    override suspend fun insertCalendarEvent(calendarEventDto: CalendarEventDto) {
        calendarDao.insertCalendarEvent(calendarEventDto)
    }

    override suspend fun getAllCalendarEventsFromCurrentMonth(
        firstDayOfMonth: Long,
        firstDayOfNextMonth: Long
    ): List<CalendarEventDto> {
        return calendarDao.getAllCalendarEventsFromCurrentMonth(
            firstDayOfMonth = firstDayOfMonth,
            firstDayOfNextMonth = firstDayOfNextMonth
        )
    }

    override suspend fun getAllCalendarEvents(): List<CalendarEventDto> {
        return calendarDao.getAllCalendarEvents()
    }
}