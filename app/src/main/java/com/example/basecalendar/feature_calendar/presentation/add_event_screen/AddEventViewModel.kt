package com.example.basecalendar.feature_calendar.presentation.add_event_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(

): ViewModel() {
    // TODO - finish

    private val _state = mutableStateOf(AddEventState())
    val state: State<AddEventState> = _state

    init {
        getCurrentStartingAndEndingDate()
    }

    fun setStateTitle(title: String) {
        _state.value = state.value.copy(
            title = title
        )
    }

    fun setStateIsTakingWholeDay(isTakingWholeDay: Boolean) {
        _state.value = state.value.copy(
            isTakingWholeDay = isTakingWholeDay
        )
    }

    fun setStateStartingDate(startingDate: Long) {
        val sdf = SimpleDateFormat("EE, d MMM yyyy", Locale.getDefault())

        _state.value = state.value.copy(
            startingDate = startingDate
        )
    }
    fun setStateEndingDate(endingDate: Long) {
        val sdf = SimpleDateFormat("EE, d MMM yyyy", Locale.getDefault())

        _state.value = state.value.copy(
            startingDate = endingDate
        )
    }

    fun setStartingDateHourAndMinutes(hour: Int, minutes: Int) {

        val c = Calendar.getInstance()

        c.timeInMillis = state.value.startingDate
        c.set(Calendar.HOUR, hour)
        c.set(Calendar.MINUTE, minutes)

        _state.value = state.value.copy(
            startingDate = c.timeInMillis
        )
    }
    fun setEndingDateHourAndMinutes(hour: Int, minutes: Int) {

        val c = Calendar.getInstance()

        c.timeInMillis = state.value.endingDate
        c.set(Calendar.HOUR, hour)
        c.set(Calendar.MINUTE, minutes)

        _state.value = state.value.copy(
            endingDate = c.timeInMillis
        )
    }

    private fun getCurrentStartingAndEndingDate() {
        val sdf = SimpleDateFormat("EE, d MMM yyyy", Locale.getDefault())

        _state.value = state.value.copy(
            startingDate = System.currentTimeMillis(),
            endingDate = System.currentTimeMillis() + 3600000
        )
    }
}