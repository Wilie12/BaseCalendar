package com.example.basecalendar.di

import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository
import com.example.basecalendar.feature_calendar.domain.use_case.GetAllCalendarEvents
import com.example.basecalendar.feature_calendar.domain.use_case.GetCurrentDate
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfMonthInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfNextMonthInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfNextYearInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.GetFirstDayOfYearInMillis
import com.example.basecalendar.feature_calendar.domain.use_case.day.DayUseCases
import com.example.basecalendar.feature_calendar.domain.use_case.main.GetCalendarWithEvents
import com.example.basecalendar.feature_calendar.domain.use_case.main.GetEmptyCalendar
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DayModule {

    @Provides
    @ViewModelScoped
    fun provideDayUseCase(repository: CalendarRepository): DayUseCases {
        return DayUseCases(
            getCurrentDate = GetCurrentDate(),
            getFirstDayOfNextMonthInMillis = GetFirstDayOfNextMonthInMillis(),
            getFirstDayOfMonthInMillis = GetFirstDayOfMonthInMillis(),
            getEmptyCalendar = GetEmptyCalendar(),
            getCalendarWithEvents = GetCalendarWithEvents(),
            getAllCalendarEvents = GetAllCalendarEvents(repository),
            getFirstDayOfYearInMillis = GetFirstDayOfYearInMillis(),
            getFirstDayOfNextYearInMillis = GetFirstDayOfNextYearInMillis()
        )
    }
}