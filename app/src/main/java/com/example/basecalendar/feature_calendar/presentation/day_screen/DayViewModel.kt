package com.example.basecalendar.feature_calendar.presentation.day_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
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
    private val dayUseCases: DayUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(DayState())
    val state: State<DayState> = _state

    init {
        getCurrentDate(checkNotNull(savedStateHandle["day"]))
        viewModelScope.launch {
            getAllEvents(state.value.selectedDate.year)
            setFullCalendar(
                selectedMonth = state.value.selectedDate.month,
                selectedYear = state.value.selectedDate.year
            )
            getCalendarEventsFromCurrentDay(state.value.selectedDate.day)
        }
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
                    if (previousMonth >= Calendar.JANUARY) {
                        selectedMonth = previousMonth
                        c.set(Calendar.MONTH, selectedMonth)
                        c.set(Calendar.YEAR, selectedYear)
                        previousDay = c.getActualMaximum(Calendar.DAY_OF_MONTH)
                    } else {
                        previousMonth = Calendar.DECEMBER
                        selectedMonth = previousMonth
                        selectedYear = previousYear
                        c.set(Calendar.MONTH, selectedMonth)
                        c.set(Calendar.YEAR, selectedYear)
                        previousDay = c.getActualMaximum(Calendar.DAY_OF_MONTH)
                    }
                } else {
                    c.set(Calendar.MONTH, selectedMonth)
                    c.set(Calendar.YEAR, selectedYear)
                }

                if (selectedMonth != state.value.selectedDate.month) {
                    if (selectedYear != state.value.selectedDate.year) {
                        viewModelScope.launch {
                            getAllEvents(selectedYear)
                            setFullCalendar(
                                selectedMonth = selectedMonth,
                                selectedYear = selectedYear
                            )
                        }
                    } else {
                        setFullCalendar(
                            selectedMonth = selectedMonth,
                            selectedYear = selectedYear
                        )
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

                if (state.value.currentDate.month != state.value.selectedDate.month) {
                    if (state.value.currentDate.year != state.value.selectedDate.year) {
                        viewModelScope.launch {
                            getAllEvents(state.value.currentDate.year)
                            setFullCalendar(
                                selectedMonth = state.value.currentDate.month,
                                selectedYear = state.value.currentDate.year
                            )
                        }
                    } else {
                        setFullCalendar(
                            selectedMonth = state.value.currentDate.month,
                            selectedYear = state.value.currentDate.year
                        )
                    }

                }

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

                c.set(Calendar.MONTH, selectedMonth)
                c.set(Calendar.YEAR, selectedYear)

                if (nextDay > c.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    if (nextMonth > Calendar.DECEMBER) {
                        nextMonth = Calendar.JANUARY
                        selectedMonth = nextMonth
                        selectedYear = nextYear
                        c.set(Calendar.MONTH, selectedMonth)
                        c.set(Calendar.YEAR, selectedYear)
                        nextDay = c.getActualMinimum(Calendar.DAY_OF_MONTH)
                    } else {
                        selectedMonth = nextMonth
                        c.set(Calendar.MONTH, selectedMonth)
                        c.set(Calendar.YEAR, selectedYear)
                        nextDay = c.getActualMinimum(Calendar.DAY_OF_MONTH)
                    }
                } else {
                    c.set(Calendar.MONTH, selectedMonth)
                    c.set(Calendar.YEAR, selectedYear)
                }

                if (selectedMonth != state.value.selectedDate.month) {
                    if (selectedYear != state.value.selectedDate.year) {
                        viewModelScope.launch {
                            getAllEvents(selectedYear)
                            setFullCalendar(
                                selectedMonth = selectedMonth,
                                selectedYear = selectedYear
                            )
                        }
                    } else {
                        setFullCalendar(
                            selectedMonth = selectedMonth,
                            selectedYear = selectedYear
                        )
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

            is DayEvent.SetDay -> {

                if (event.value > 0) {
                    val c = Calendar.getInstance()

                    c.set(Calendar.MONTH, state.value.selectedDate.month)
                    c.set(Calendar.YEAR, state.value.selectedDate.year)
                    c.set(Calendar.DAY_OF_MONTH, event.value)

                    _state.value = state.value.copy(
                        selectedDate = CalendarDate(
                            day = event.value,
                            month = state.value.selectedDate.month,
                            year = state.value.selectedDate.year
                        ),
                        dayOfWeek = c.get(Calendar.DAY_OF_WEEK)
                    )
                    getCalendarEventsFromCurrentDay(selectedDay = state.value.selectedDate.day)
                }
            }
        }
    }

    private fun getCalendarEventsFromCurrentDay(
        selectedDay: Int
    ) {

        val c = Calendar.getInstance()

        val listOfEvents = state.value.listOfEventsFromCurrentMonth
        _state.value = state.value.copy(
            listOfEventsFromCurrentDay = listOfEvents.filter { calendarEvent ->
                c.timeInMillis = calendarEvent.startingDate
                val eventStartingDay = c.get(Calendar.DAY_OF_MONTH)
                c.timeInMillis = calendarEvent.endingDate
                val eventEndingDay = c.get(Calendar.DAY_OF_MONTH)

                selectedDay in eventStartingDay..eventEndingDay
            }
        )
    }

    private fun getCurrentDate(
        initialDay: Int = 0
    ) {
        val currentDate = dayUseCases.getCurrentDate()

        val c = Calendar.getInstance()

        _state.value = state.value.copy(
            selectedDate = CalendarDate(
                day = if (initialDay != 0) initialDay else currentDate.day,
                month = currentDate.month,
                year = currentDate.year
            ),
            currentDate = CalendarDate(
                day = currentDate.day,
                month = currentDate.month,
                year = currentDate.year
            ),
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK),
            currentHour = c.get(Calendar.HOUR_OF_DAY),
            currentMinutes = c.get(Calendar.MINUTE)
        )
    }

    private fun getAllCalendarEventsFromCurrentMonth() {
        val firstDayOfMonth = dayUseCases.getFirstDayOfMonthInMillis(
            currentMonth = state.value.selectedDate.month,
            currentYear = state.value.selectedDate.year
        )
        val firstDayOfNextMonth = dayUseCases.getFirstDayOfNextMonthInMillis(
            currentMonth = state.value.selectedDate.month,
            currentYear = state.value.selectedDate.year
        )

        val listOfEvents = state.value.listOfEvents.filter {
            it.startingDate in firstDayOfMonth..firstDayOfNextMonth
                    || it.endingDate in firstDayOfMonth..firstDayOfNextMonth
        }
        _state.value = state.value.copy(
            listOfEventsFromCurrentMonth = listOfEvents
        )
    }

    private fun setFullCalendar(
        selectedMonth: Int,
        selectedYear: Int
    ) {
        setEmptyCalendar(
            selectedMonth = selectedMonth,
            selectedYear = selectedYear
        )
        getAllCalendarEventsFromCurrentMonth()

        if (state.value.listOfEvents.isNotEmpty()) {
            setCalendarWithEvents()
        }
    }

    private fun setCalendarWithEvents() {
        val listOfDays = dayUseCases.getCalendarWithEvents(
            listOfDays = state.value.listOfDays,
            listOfEvents = state.value.listOfEventsFromCurrentMonth
        )

        _state.value = state.value.copy(
            listOfDays = listOfDays
        )
    }

    private fun setEmptyCalendar(
        selectedMonth: Int,
        selectedYear: Int
    ) {

        val listOfDays = dayUseCases.getEmptyCalendar(
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

    private suspend fun getAllEvents(
        selectedYear: Int
    ) {
        val listOfEvents = dayUseCases.getAllCalendarEventsFromCurrentYear(
            firstDayOfYear = dayUseCases.getFirstDayOfYearInMillis(selectedYear),
            firstDayOfNextYear = dayUseCases.getFirstDayOfNextYearInMillis(selectedYear)
        )

        _state.value = state.value.copy(
            listOfEvents = listOfEvents
        )
    }
}