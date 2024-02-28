package com.example.basecalendar.feature_calendar.data.local_data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto

@Dao
interface CalendarDao {

    @Insert
    suspend fun insertCalendarEvent(calendarEventDto: CalendarEventDto)

    @Query("SELECT * FROM calendar_event_table " +
            "WHERE starting_date >= :firstDayOfMonth and starting_date < :firstDayOfNextMonth")
    suspend fun getAllCalendarEventsFromCurrentMonth(
        firstDayOfMonth: Long,
        firstDayOfNextMonth: Long
    ): List<CalendarEventDto>

    @Query("SELECT * FROM calendar_event_table")
    suspend fun getAllCalendarEvents(): List<CalendarEventDto>
}