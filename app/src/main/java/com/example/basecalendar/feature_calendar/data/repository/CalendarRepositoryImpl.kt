package com.example.basecalendar.feature_calendar.data.repository

import com.example.basecalendar.feature_calendar.data.local_data_source.CalendarDao
import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository
import javax.inject.Inject

class CalendarRepositoryImpl(
    private val calendarDao: CalendarDao
): CalendarRepository {

    override suspend fun insertCalendarEvent(calendarEventDto: CalendarEventDto): Long {
        return calendarDao.insertCalendarEvent(calendarEventDto)
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

    override suspend fun getCalendarEventById(eventId: Int): CalendarEventDto {
        return calendarDao.getCalendarEventById(eventId)
    }

    override suspend fun deleteCalendarEvent(calendarEventDto: CalendarEventDto) {
        calendarDao.deleteCalendarEvent(calendarEventDto)
    }

    override suspend fun updateCalendarEvent(calendarEventDto: CalendarEventDto) {
        calendarDao.updateCalendarEvent(calendarEventDto)
    }
}