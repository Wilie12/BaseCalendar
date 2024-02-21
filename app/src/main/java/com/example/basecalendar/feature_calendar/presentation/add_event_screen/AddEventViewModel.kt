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
) : ViewModel() {

    private val _state = mutableStateOf(AddEventState())
    val state: State<AddEventState> = _state

    init {
        getCurrentStartingAndEndingDate()
    }

    fun onEvent(event: AddEventEvent) {
        when (event) {
            is AddEventEvent.ChangeColor -> {
                _state.value = state.value.copy(
                    color = event.value
                )
            }

            is AddEventEvent.ChangeDescription -> {
                _state.value = state.value.copy(
                    description = event.value
                )
            }

            is AddEventEvent.ChangeEndingDate -> {
                _state.value = state.value.copy(
                    endingDate = event.value
                )
            }

            is AddEventEvent.ChangeStartingHourAndMinutes -> {
                _state.value = state.value.copy(
                    startingDate = addEventUseCases.getSelectedHourAndMinutesInMillis(
                        date = state.value.startingDate,
                        hour = event.hour,
                        minutes = event.minutes
                    )
                )
            }

            is AddEventEvent.ChangeEndingHourAndMinutes -> {
                _state.value = state.value.copy(
                    endingDate = addEventUseCases.getSelectedHourAndMinutesInMillis(
                        date = state.value.endingDate,
                        hour = event.hour,
                        minutes = event.minutes
                    )
                )
            }

            is AddEventEvent.ChangeIsTakingWholeDay -> {
                _state.value = state.value.copy(
                    isTakingWholeDay = event.value
                )
            }

            is AddEventEvent.ChangeReminderMode -> {
                _state.value = state.value.copy(
                    reminderMode = event.value
                )
            }

            is AddEventEvent.ChangeRepeatMode -> {
                _state.value = state.value.copy(
                    repeatMode = event.value
                )
            }

            is AddEventEvent.ChangeStartingDate -> {
                _state.value = state.value.copy(
                    startingDate = event.value
                )
            }

            is AddEventEvent.EnteredTitle -> {
                _state.value = state.value.copy(
                    title = event.value
                )
            }

            AddEventEvent.SaveEvent -> {
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
        }
    }

    private fun getCurrentStartingAndEndingDate() {
        _state.value = state.value.copy(
            startingDate = System.currentTimeMillis(),
            endingDate = System.currentTimeMillis() + 3600000
        )
    }
}