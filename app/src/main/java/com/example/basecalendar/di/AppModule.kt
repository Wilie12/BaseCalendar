package com.example.basecalendar.di

import android.app.Application
import androidx.room.Room
import com.example.basecalendar.feature_calendar.data.alarm.AlarmScheduler
import com.example.basecalendar.feature_calendar.data.alarm.AlarmSchedulerImpl
import com.example.basecalendar.feature_calendar.data.local_data_source.CalendarDatabase
import com.example.basecalendar.feature_calendar.data.repository.CalendarRepositoryImpl
import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCalendarDatabase(app: Application): CalendarDatabase {
        return Room.databaseBuilder(
            app,
            CalendarDatabase::class.java,
            CalendarDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCalendarRepository(db: CalendarDatabase): CalendarRepository {
        return CalendarRepositoryImpl(db.calendarDao)
    }

    @Provides
    @Singleton
    fun provideAlarmScheduler(app: Application): AlarmScheduler {
        return AlarmSchedulerImpl(app.applicationContext)
    }
}