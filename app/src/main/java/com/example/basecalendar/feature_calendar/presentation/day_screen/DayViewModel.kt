package com.example.basecalendar.feature_calendar.presentation.day_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basecalendar.feature_calendar.data.util.CalendarDate
import com.example.basecalendar.feature_calendar.domain.use_case.day.DayUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class DayViewModel @Inject constructor(
    private val dayUseCases: DayUseCases
) : ViewModel() {

    private val _state = mutableStateOf(DayState())
    val state: State<DayState> = _state

    init {
        getCurrentDate()
    }

    fun onEvent(event: DayEvent) {
        when (event) {
            is DayEvent.PreviousDay -> {

                val c = Calendar.getInstance()

                var previousDay = state.value.selectedDate.day - 1
                var previousMonth = state.value.selectedDate.month - 1
                val previousYear = state.value.selectedDate.year - 1

                var selectedMonth = state.value.selectedDate.month
                var selectedYear = state.value.selectedDate.year

                if (previousDay < 1) {
                    if (previousMonth >= 0) {
                        selectedMonth = previousMonth
                        c.set(Calendar.MONTH, selectedMonth)
                        c.set(Calendar.YEAR, selectedYear)
                        previousDay = c.getActualMaximum(Calendar.DAY_OF_MONTH)
                    }
                    else {
                        previousMonth = 11
                        selectedMonth = previousMonth
                        selectedYear = previousYear
                        c.set(Calendar.MONTH, selectedMonth)
                        c.set(Calendar.YEAR, selectedYear)
                        previousDay = c.getActualMaximum(Calendar.DAY_OF_MONTH)
                    }
                }

                c.set(Calendar.DAY_OF_MONTH, previousDay)

                _state.value = state.value.copy(
                    selectedDate = CalendarDate(
                        day = previousDay,
                        month = selectedMonth,
                        year = selectedYear
                    ),
                    dayOfWeek = c.get(Calendar.DAY_OF_WEEK)
                )
                getCalendarEventsFromCurrentDay(selectedDay = state.value.selectedDate.day)
            }

            is DayEvent.CurrentDay -> {

                val c = Calendar.getInstance()

                _state.value = state.value.copy(
                    selectedDate = CalendarDate(
                        day = state.value.currentDate.day,
                        month = state.value.currentDate.month,
                        year = state.value.currentDate.year
                    ),
                    dayOfWeek = c.get(Calendar.DAY_OF_WEEK)
                )
                getCalendarEventsFromCurrentDay(selectedDay = state.value.selectedDate.day)
            }

            is DayEvent.NextDay -> {

                val c = Calendar.getInstance()

                var nextDay = state.value.selectedDate.day + 1
                var nextMonth = state.value.selectedDate.month + 1
                val nextYear = state.value.selectedDate.year + 1

                var selectedMonth = state.value.selectedDate.month
                var selectedYear = state.value.selectedDate.year

                if (nextDay > c.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    if (nextMonth > 11) {
                        nextMonth = 0
                        selectedMonth = nextMonth
                        selectedYear = nextYear
                        c.set(Calendar.MONTH, selectedMonth)
                        c.set(Calendar.YEAR, selectedYear)
                        nextDay = c.getActualMinimum(Calendar.DAY_OF_MONTH)
                    }
                    else {
                        selectedMonth = nextMonth
                        c.set(Calendar.MONTH, selectedMonth)
                        c.set(Calendar.YEAR, selectedYear)
                        nextDay = c.getActualMinimum(Calendar.DAY_OF_MONTH)
                    }
                }
                c.set(Calendar.DAY_OF_MONTH, nextDay)

                _state.value = state.value.copy(
                    selectedDate = CalendarDate(
                        day = nextDay,
                        month = selectedMonth,
                        year = selectedYear
                    ),
                    dayOfWeek = c.get(Calendar.DAY_OF_WEEK)
                )
                getCalendarEventsFromCurrentDay(selectedDay = state.value.selectedDate.day)
            }
        }
    }

    // TODO

    private fun getCalendarEventsFromCurrentDay(
        selectedDay: Int
    ) {

        val c = Calendar.getInstance()

        viewModelScope.launch {

            _state.value = state.value.copy(isLoading = true)

            getAllCalendarEventsFromCurrentMonth()

            val listOfEvents = state.value.listOfEvents
            _state.value = state.value.copy(
                listOfEventsFromCurrentDay = listOfEvents.filter { calendarEvent ->
                    c.timeInMillis = calendarEvent.startingDate
                    val eventStartingDay = c.get(Calendar.DAY_OF_MONTH)
                    c.timeInMillis = calendarEvent.endingDate
                    val eventEndingDay = c.get(Calendar.DAY_OF_MONTH)

                    selectedDay in eventStartingDay..eventEndingDay
                }
            )

            _state.value = state.value.copy(isLoading = false)
        }
    }

    private fun getCurrentDate() {

        val currentDate = dayUseCases.getCurrentDate()

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

    private suspend fun getAllCalendarEventsFromCurrentMonth() {
        val firstDayOfMonth = dayUseCases.getFirstDayOfMonthInMillis(
            state.value.selectedDate.month,
            state.value.selectedDate.year
        )
        val firstDayOfNextMonth = dayUseCases.getFirstDayOfNextMonthInMillis(
            state.value.selectedDate.month,
            state.value.selectedDate.year
        )

        val listOfEvents = dayUseCases.getAllCalendarEventsFromCurrentMonth(
            firstDayOfMonth,
            firstDayOfNextMonth
        )
        _state.value = state.value.copy(
            listOfEvents = listOfEvents
        )
    }
}