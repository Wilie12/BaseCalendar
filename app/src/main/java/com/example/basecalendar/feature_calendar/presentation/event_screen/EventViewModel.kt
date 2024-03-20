package com.example.basecalendar.feature_calendar.presentation.event_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basecalendar.feature_calendar.domain.use_case.event.EventUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventUseCases: EventUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(EventState())
    val state: State<EventState> = _state

    init {
        getCalendarEvent(checkNotNull(savedStateHandle["eventId"]))
        setScreenRoute(checkNotNull(savedStateHandle["screen"]))
    }

    private fun getCalendarEvent(eventId: Int) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)

            val calendarEvent = eventUseCases.getCalendarEventById(eventId)
            _state.value = state.value.copy(
                event = calendarEvent
            )

            _state.value = state.value.copy(isLoading = false)
        }
    }

    private fun setScreenRoute(route: String) {
        _state.value = state.value.copy(screenRoute = route)
    }
}