package com.example.basecalendar.feature_calendar.presentation.event_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class EventViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf(EventState())
    val state: State<EventState> = _state

    // TODO - finish viewModel
    // TODO - add useCases to receive event
}