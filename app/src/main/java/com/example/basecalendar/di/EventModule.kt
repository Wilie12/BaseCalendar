package com.example.basecalendar.di

import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository
import com.example.basecalendar.feature_calendar.domain.use_case.event.EventUseCases
import com.example.basecalendar.feature_calendar.domain.use_case.event.GetCalendarEventById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object EventModule {

    @Provides
    @ViewModelScoped
    fun provideEventUseCases(repository: CalendarRepository): EventUseCases {
        return EventUseCases(
            getCalendarEventById = GetCalendarEventById(repository)
        )
    }
}