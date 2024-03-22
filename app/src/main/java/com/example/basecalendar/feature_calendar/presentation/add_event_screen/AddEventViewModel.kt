package com.example.basecalendar.feature_calendar.presentation.add_event_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basecalendar.feature_calendar.data.alarm.AlarmItem
import com.example.basecalendar.feature_calendar.data.alarm.AlarmScheduler
import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.data.util.ReminderMode
import com.example.basecalendar.feature_calendar.data.util.RepeatMode
import com.example.basecalendar.feature_calendar.domain.use_case.add_event.AddEventUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val addEventUseCases: AddEventUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AddEventState())
    val state: State<AddEventState> = _state

    @Inject
    lateinit var alarmScheduler: AlarmScheduler

    init {
        getCurrentStartingAndEndingDate()
        setStateScreenRoute(checkNotNull(savedStateHandle["screen"]))
        if (checkNotNull(savedStateHandle["eventId"]) != 0) {
            getCalendarEventById(checkNotNull(savedStateHandle["eventId"]))
        }
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
                val c = Calendar.getInstance()
                c.timeInMillis = state.value.endingDate
                val hour = c.get(Calendar.HOUR_OF_DAY)
                val minutes = c.get(Calendar.MINUTE)

                c.timeInMillis = event.value
                c.set(Calendar.HOUR_OF_DAY, hour)
                c.set(Calendar.MINUTE, minutes)
                _state.value = state.value.copy(
                    endingDate = c.timeInMillis
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
                    repeatMode = event.value,
                    isRepeating = (event.value != RepeatMode.NONE)
                )
            }

            is AddEventEvent.ChangeStartingDate -> {

                val c = Calendar.getInstance()
                c.timeInMillis = state.value.startingDate
                val hour = c.get(Calendar.HOUR_OF_DAY)
                val minutes = c.get(Calendar.MINUTE)

                c.timeInMillis = event.value
                c.set(Calendar.HOUR_OF_DAY, hour)
                c.set(Calendar.MINUTE, minutes)
                _state.value = state.value.copy(
                    startingDate = c.timeInMillis
                )
            }

            is AddEventEvent.EnteredTitle -> {
                _state.value = state.value.copy(
                    title = event.value
                )
            }

            is AddEventEvent.DismissDialog -> {

                val queue = state.value.permissionDialogQueue.toMutableList()
                queue.removeLast()

                _state.value = state.value.copy(
                    permissionDialogQueue = queue.toList()
                )

            }

            is AddEventEvent.PermissionResult -> {

                val queue = state.value.permissionDialogQueue.toMutableList()

                if (!event.isGranted) {

                    queue.add(event.permission)

                    _state.value = state.value.copy(
                        permissionDialogQueue = queue.toList()
                    )
                }
            }

            AddEventEvent.SaveEvent -> {
                viewModelScope.launch {
                    if (state.value.endingDate > state.value.startingDate || state.value.isTakingWholeDay) {
                        if (checkNotNull(savedStateHandle["eventId"]) != 0) {
                            if (checkNotNull(savedStateHandle["isDuplicate"])) {
                                addEventUseCases.addEvent(
                                    CalendarEventDto(
                                        id = 0,
                                        startingDate = if (state.value.isTakingWholeDay) {
                                            addEventUseCases.getStartOfDay(state.value.startingDate)
                                        } else {
                                            state.value.startingDate

                                        },
                                        endingDate = if (state.value.isTakingWholeDay) {
                                            addEventUseCases.getStartOfDay(state.value.endingDate)
                                        } else {
                                            state.value.endingDate

                                        },
                                        isTakingWholeDay = state.value.isTakingWholeDay,
                                        isRepeating = state.value.isRepeating,
                                        repeatMode = state.value.repeatMode,
                                        title = state.value.title,
                                        description = state.value.description,
                                        color = state.value.color,
                                        reminderMode = state.value.reminderMode
                                    )
                                )
                            } else {
                                addEventUseCases.updateCalendarEvent(
                                    CalendarEventDto(
                                        id = checkNotNull(savedStateHandle["eventId"]),
                                        startingDate = if (state.value.isTakingWholeDay) {
                                            addEventUseCases.getStartOfDay(state.value.startingDate)
                                        } else {
                                            state.value.startingDate

                                        },
                                        endingDate = if (state.value.isTakingWholeDay) {
                                            addEventUseCases.getStartOfDay(state.value.endingDate)
                                        } else {
                                            state.value.endingDate

                                        },
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
                        } else {
                            addEventUseCases.addEvent(
                                CalendarEventDto(
                                    id = 0,
                                    startingDate = if (state.value.isTakingWholeDay) {
                                        addEventUseCases.getStartOfDay(state.value.startingDate)
                                    } else {
                                        state.value.startingDate

                                    },
                                    endingDate = if (state.value.isTakingWholeDay) {
                                        addEventUseCases.getStartOfDay(state.value.endingDate)
                                    } else {
                                        state.value.endingDate

                                    },
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
                        if (state.value.reminderMode != ReminderMode.NONE) {
                            scheduleAlarmItem(
                                AlarmItem(
                                    time = state.value.startingDate,
                                    title = state.value.title,
                                    reminderMode = state.value.reminderMode
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setStateScreenRoute(route: String) {
        _state.value = state.value.copy(
            screenRoute = route
        )
    }

    private fun getCurrentStartingAndEndingDate() {
        _state.value = state.value.copy(
            startingDate = System.currentTimeMillis(),
            endingDate = System.currentTimeMillis() + 3600000
        )
    }

    private fun scheduleAlarmItem(item: AlarmItem) {
        alarmScheduler.schedule(item)
    }

    private fun getCalendarEventById(eventId: Int) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)

            val event = addEventUseCases.getCalendarEventById(eventId)
            _state.value = state.value.copy(
                title = event.title,
                isTakingWholeDay = event.isTakingWholeDay,
                startingDate = event.startingDate,
                endingDate = event.endingDate,
                isRepeating = event.isRepeating,
                repeatMode = event.repeatMode,
                reminderMode = event.reminderMode,
                color = event.color,
                description = event.description
            )
            _state.value = state.value.copy(isLoading = false)
        }
    }
}