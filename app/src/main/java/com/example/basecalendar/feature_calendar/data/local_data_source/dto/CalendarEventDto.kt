package com.example.basecalendar.feature_calendar.data.local_data_source.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calendar_event_table")
data class CalendarEventDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "starting_date")
    val startingDate: Long,
    @ColumnInfo(name = "ending_date")
    val endingDate: Long,
    @ColumnInfo(name = "is_taking_whole_day")
    val isTakingWholeDay: Boolean,
    @ColumnInfo(name = "is_repeating")
    val isRepeating: Boolean,
    @ColumnInfo(name = "repeat_mode")
    val repeatMode: Int,
    val title: String,
    val description: String,
    val color: Int,
    @ColumnInfo(name = "reminder_mode")
    val reminderMode: Int
)
