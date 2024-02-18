package com.example.basecalendar.feature_calendar.presentation.add_event_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.data.util.Constants
import com.example.basecalendar.feature_calendar.domain.use_case.add_event.AddEventUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val addEventUseCases: AddEventUseCases
): ViewModel() {
    // TODO - finish

    private val _state = mutableStateOf(AddEventState())
    val state: State<AddEventState> = _state

    init {
        getCurrentStartingAndEndingDate()
        setStateColor(Constants.blue)
    }

    fun saveEvent() {
        viewModelScope.launch {
            addEventUseCases.addEvent(
                CalendarEventDto(
                    id = 0,
                    startingDate = state.value.startingDate,
                    endingDate = state.value.endingDate,
                    isTakingWholeDay = state.value.isTakingWholeDay,
                    isRepeating = state.value.isRepeating,
                    repeatMode = state.value.repeatMode,
                    title = state.value.title,
                    description = state.value.description,
                    color = state.value.color,
                    reminderMode = state.value.reminderMode
                )
            )
        }
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
        _state.value = state.value.copy(
            startingDate = startingDate
        )
    }
    fun setStateEndingDate(endingDate: Long) {
        _state.value = state.value.copy(
            endingDate = endingDate
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

    fun setStateRepeatMode(repeatMode: Int) {
        _state.value = state.value.copy(
            repeatMode = repeatMode
        )
    }

    fun setStateReminderMode(reminderMode: Int) {
        _state.value = state.value.copy(
            reminderMode = reminderMode
        )
    }

    fun setStateColor(color: Int) {
        _state.value = state.value.copy(
            color = color
        )
    }

    fun setStateDescription(description: String) {
        _state.value = state.value.copy(
            description = description
        )
    }

    private fun getCurrentStartingAndEndingDate() {
        _state.value = state.value.copy(
            startingDate = System.currentTimeMillis(),
            endingDate = System.currentTimeMillis() + 3600000
        )
    }
}