package com.example.basecalendar.feature_calendar.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basecalendar.feature_calendar.data.util.CalendarDate
import com.example.basecalendar.feature_calendar.domain.use_case.main.MainUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCases: MainUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        getCurrentDate()
        setEmptyCalendar(
            selectedMonth = state.value.selectedDate.month,
            selectedYear = state.value.selectedDate.year
        )
        viewModelScope.launch {
            getAllEvents(state.value.selectedDate.year)
            setFullCalendarForSelectedMonth(
                selectedMonth = state.value.selectedDate.month,
                selectedYear = state.value.selectedDate.year
            )
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            MainEvent.CurrentMonth -> {
                if (state.value.currentDate.month != state.value.selectedDate.month) {
                    if (state.value.currentDate.year != state.value.selectedDate.year) {
                        viewModelScope.launch {
                            getAllEvents(state.value.currentDate.year)
                            setFullCalendarForSelectedMonth(
                                selectedMonth = state.value.currentDate.month,
                                selectedYear = state.value.currentDate.year
                            )
                        }
                    }
                }
            }

            MainEvent.NextMonth -> {
                var nextMonth = state.value.selectedDate.month + 1

                var selectedMonth = state.value.selectedDate.month
                var selectedYear = state.value.selectedDate.year

                if (nextMonth > Calendar.DECEMBER) {
                    nextMonth = Calendar.JANUARY
                    selectedMonth = nextMonth
                    selectedYear++
                } else {
                    selectedMonth++
                }

                if (selectedYear != state.value.selectedDate.year) {
                    viewModelScope.launch {
                        getAllEvents(selectedYear)
                        setFullCalendarForSelectedMonth(
                            selectedMonth = selectedMonth,
                            selectedYear = selectedYear
                        )
                    }
                } else {
                    setFullCalendarForSelectedMonth(
                        selectedMonth = selectedMonth,
                        selectedYear = selectedYear
                    )
                }
            }

            MainEvent.PreviousMonth -> {
                var previousMonth = state.value.selectedDate.month - 1

                var selectedMonth = state.value.selectedDate.month
                var selectedYear = state.value.selectedDate.year

                if (previousMonth < Calendar.JANUARY) {
                    previousMonth = Calendar.DECEMBER
                    selectedMonth = previousMonth
                    selectedYear--
                } else {
                    selectedMonth--
                }

                if (selectedYear != state.value.selectedDate.year) {
                    viewModelScope.launch {
                        getAllEvents(selectedYear)
                        setFullCalendarForSelectedMonth(
                            selectedMonth = selectedMonth,
                            selectedYear = selectedYear
                        )
                    }
                } else {
                    setFullCalendarForSelectedMonth(
                        selectedMonth = selectedMonth,
                        selectedYear = selectedYear
                    )
                }
            }
        }
    }

    private fun setFullCalendarForSelectedMonth(
        selectedMonth: Int,
        selectedYear: Int
    ) {

        setEmptyCalendar(
            selectedMonth,
            selectedYear
        )
        _state.value = state.value.copy(isLoading = true)

        getAllCalendarEventsFromCurrentMonth()
        if (state.value.listOfEventsFromCurrentMonth.isNotEmpty()) {
            addEventsToCalendar()
        }

        _state.value = state.value.copy(isLoading = false)
    }

    private fun addEventsToCalendar() {

        val listOfDays = mainUseCases.getCalendarWithEvents(
            listOfDays = state.value.listOfDays,
            listOfEvents = state.value.listOfEventsFromCurrentMonth
        )

        _state.value = state.value.copy(
            listOfDays = listOfDays
        )
    }

    private fun getAllCalendarEventsFromCurrentMonth() {

        val firstDayOfMonth = mainUseCases.getFirstDayOfMonthInMillis(
            state.value.selectedDate.month,
            state.value.selectedDate.year
        )
        val firstDayOfNextMonth = mainUseCases.getFirstDayOfNextMonthInMillis(
            state.value.selectedDate.month,
            state.value.selectedDate.year
        )

        val listOfEvents = state.value.listOfEvents.filter {
            it.startingDate in firstDayOfMonth..firstDayOfNextMonth
                    || it.endingDate in firstDayOfMonth..firstDayOfNextMonth
        }
        _state.value = state.value.copy(
            listOfEventsFromCurrentMonth = listOfEvents
        )
    }

    private fun setEmptyCalendar(
        selectedMonth: Int,
        selectedYear: Int
    ) {
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
            listOfEventsFromCurrentMonth = emptyList()
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

    private suspend fun getAllEvents(
        selectedYear: Int
    ) {
        val listOfEvents = mainUseCases.getAllCalendarEvents(
            firstDayOfYear = mainUseCases.getFirstDayOfYearInMillis(selectedYear),
            firstDayOfNextYear = mainUseCases.getFirstDayOfNextYearInMillis(selectedYear)
        )

        _state.value = state.value.copy(
            listOfEvents = listOfEvents
        )
    }
}

