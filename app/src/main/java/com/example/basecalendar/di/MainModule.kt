package com.example.basecalendar.di

import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository
import com.example.basecalendar.feature_calendar.domain.use_case.GetAllCalendarEventsFromCurrentYear
import com.example.basecalendar.feature_calendar.domain.use_case.GetCurrentDate
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfMonthInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfNextMonthInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfNextYearInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfYearInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.main.GetCalendarWithEvents
import com.example.basecalendar.feature_calendar.domain.use_case.main.GetEmptyCalendar
import com.example.basecalendar.feature_calendar.domain.use_case.main.MainUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MainModule {

    @Provides
    @ViewModelScoped
    fun provideMainUseCases(repository: CalendarRepository): MainUseCases {
        return MainUseCases(
            getFirstDayOfMonthInMillis = GetFirstDayOfMonthInMillis(),
            getFirstDayOfNextMonthInMillis = GetFirstDayOfNextMonthInMillis(),
            getEmptyCalendar = GetEmptyCalendar(),
            getCurrentDate = GetCurrentDate(),
            getCalendarWithEvents = GetCalendarWithEvents(),
            getAllCalendarEventsFromCurrentYear = GetAllCalendarEventsFromCurrentYear(repository),
            getFirstDayOfYearInMillis = GetFirstDayOfYearInMillis(),
            getFirstDayOfNextYearInMillis = GetFirstDayOfNextYearInMillis()
        )
    }
}