package com.example.basecalendar.di

import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository
import com.example.basecalendar.feature_calendar.domain.use_case.GetAllCalendarEventsFromCurrentYear
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfNextYearInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfYearInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.alarm_service.AlarmServiceUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
object AlarmServiceModule {

    @Provides
    @ServiceScoped
    fun provideAlarmServiceUseCases(repository: CalendarRepository): AlarmServiceUseCases {
        return AlarmServiceUseCases(
            getAllCalendarEventsFromCurrentYear = GetAllCalendarEventsFromCurrentYear(repository),
            getFirstDayOfYearInMillis = GetFirstDayOfYearInMillis(),
            getFirstDayOfNextYearInMillis = GetFirstDayOfNextYearInMillis()
        )
    }
}