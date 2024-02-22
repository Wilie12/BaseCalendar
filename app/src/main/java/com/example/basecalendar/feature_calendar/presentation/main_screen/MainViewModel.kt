package com.example.basecalendar.feature_calendar.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basecalendar.feature_calendar.data.util.CalendarDate
import com.example.basecalendar.feature_calendar.domain.use_case.main.MainUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCases: MainUseCases
) : ViewModel() {

    // TODO - finish MainViewModel

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        getCurrentDate()
        setEmptyCalendar(state.value.selectedDate.month)
        viewModelScope.launch {
            setFullCalendarForSelectedMonth(state.value.selectedDate.month)
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            MainEvent.CurrentMonth -> {
                getCurrentDate()
                setFullCalendarForSelectedMonth(
                    state.value.selectedDate.month
                )
            }
            MainEvent.NextMonth -> {
                setFullCalendarForSelectedMonth(
                    state.value.selectedDate.month + 1
                )
            }
            MainEvent.PreviousMonth -> {
                setFullCalendarForSelectedMonth(
                    state.value.selectedDate.month - 1
                )
            }
        }
    }

    private fun setFullCalendarForSelectedMonth(
        selectedMonth: Int
    ) {

        setEmptyCalendar(selectedMonth)

        viewModelScope.launch {

            _state.value = state.value.copy(isLoading = true)

            getAllCalendarEventsFromCurrentMonth()
            if (state.value.listOfEvents.isNotEmpty()) {
                addEventsToCalendar()
            }

            _state.value = state.value.copy(isLoading = false)
        }
    }
    private fun addEventsToCalendar() {

        val listOfDays = mainUseCases.getCalendarWithEvents(
            listOfDays = state.value.listOfDays,
            listOfEvents = state.value.listOfEvents
        )

        _state.value = state.value.copy(
            listOfDays = listOfDays
        )
    }

    private suspend fun getAllCalendarEventsFromCurrentMonth() {
        val firstDayOfMonth = mainUseCases.getFirstDayOfMonthInMillis(
            state.value.selectedDate.month,
            state.value.selectedDate.year
        )
        val firstDayOfNextMonth = mainUseCases.getFirstDayOfNextMonthInMillis(
            state.value.selectedDate.month,
            state.value.selectedDate.year
        )

        val listOfEvents = mainUseCases.getAllCalendarEventsFromCurrentMonth(
            firstDayOfMonth,
            firstDayOfNextMonth
        )
        _state.value = state.value.copy(
            listOfEvents = listOfEvents
        )
    }

    private fun setEmptyCalendar(
        selectedMonth: Int
    ) {

        var selectedMonth = selectedMonth

        var selectedYear = state.value.selectedDate.year

        if (selectedMonth < 0) {
            selectedMonth = 11
            selectedYear -= 1

        } else if (selectedMonth > 11) {
            selectedMonth = 0
            selectedYear += 1
        }

        val listOfDays = mainUseCases.getEmptyCalendar(
            selectedMonth,
            selectedYear
        )

        _state.value = state.value.copy(
            selectedDate = CalendarDate(
                day = state.value.selectedDate.day,
                month = selectedMonth,
                year = selectedYear
            ),
            listOfDays = listOfDays,
            listOfEvents = emptyList()
        )
    }

    private fun getCurrentDate() {

        val currentDate = mainUseCases.getCurrentDate()

        _state.value = state.value.copy(
            selectedDate = CalendarDate(
                day = currentDate.day,
                month = currentDate.month,
                year = currentDate.year
            ),
            currentDate = CalendarDate(
                day = currentDate.day,
                month = currentDate.month,
                year = currentDate.year
            )
        )
    }
}

