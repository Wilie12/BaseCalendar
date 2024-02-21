package com.example.basecalendar.di

import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository
import com.example.basecalendar.feature_calendar.domain.use_case.add_event.AddEvent
import com.example.basecalendar.feature_calendar.domain.use_case.add_event.AddEventUseCases
import com.example.basecalendar.feature_calendar.domain.use_case.add_event.GetSelectedHourAndMinutesInMillis
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AddEventModule {

    @Provides
    @ViewModelScoped
    fun provideAddEventUseCases(repository: CalendarRepository): AddEventUseCases {
        return AddEventUseCases(
            addEvent = AddEvent(repository),
            getSelectedHourAndMinutesInMillis = GetSelectedHourAndMinutesInMillis()
        )
    }
}