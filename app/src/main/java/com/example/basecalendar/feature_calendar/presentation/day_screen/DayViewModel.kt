package com.example.basecalendar.feature_calendar.presentation.day_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DayViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf(DayState())
    val state: State<DayState> = _state

    fun onEvent(event: DayEvent) {
        when (event) {

            else -> {}
        }
    }

    // TODO
}