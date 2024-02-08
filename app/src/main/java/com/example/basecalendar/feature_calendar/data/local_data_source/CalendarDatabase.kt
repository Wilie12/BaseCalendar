package com.example.basecalendar.feature_calendar.data.local_data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto

@Database(
    entities = [CalendarEventDto::class],
    version = 1,
    exportSchema = false
)
abstract class CalendarDatabase: RoomDatabase() {

    abstract val calendarDao: CalendarDao

    companion object {
        const val DATABASE_NAME = "calendar_db"
    }
}