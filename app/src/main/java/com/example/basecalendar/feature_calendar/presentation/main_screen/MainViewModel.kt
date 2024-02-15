package com.example.basecalendar.feature_calendar.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.data.util.ReminderMode
import com.example.basecalendar.feature_calendar.data.util.RepeatMode
import com.example.basecalendar.feature_calendar.domain.model.CalendarDay
import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository
import com.example.basecalendar.feature_calendar.domain.use_case.main.MainUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CalendarRepository,
    private val mainUseCases: MainUseCases
) : ViewModel() {

    // TODO - finish MainViewModel

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        getCurrentDate()
        setEmptyCalendar(state.value.selectedMonth)
        viewModelScope.launch {
            setFullCalendarForSelectedMonth(state.value.selectedMonth)
        }
//        addTestDataToDatabase()
    }

    fun setFullCalendarForSelectedMonth(
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

        val c = Calendar.getInstance()

        val listOfDays = state.value.listOfDays.toMutableList()
        val listOfEvents = state.value.listOfEvents

        listOfEvents.forEach { calendarEvent ->

            c.timeInMillis = calendarEvent.startingDate
            val eventDay = c.get(Calendar.DAY_OF_MONTH)

            val listOfEventsFromCurrentDay =
                listOfDays[listOfDays.indexOf(listOfDays.find { calendarDay ->
                    calendarDay.dayOfMonth == eventDay
                })].listOfEvents.toMutableList()

            listOfEventsFromCurrentDay += calendarEvent

            listOfDays[
                listOfDays.indexOf(listOfDays.find { calendarDay ->
                    calendarDay.dayOfMonth == eventDay
                })] = CalendarDay(
                listOfEvents = listOfEventsFromCurrentDay.sortedBy { it.startingDate },
                isEmpty = false,
                dayOfMonth = eventDay
            )
        }

        _state.value = state.value.copy(
            listOfDays = listOfDays.toList()
        )
    }

    private suspend fun getAllCalendarEventsFromCurrentMonth() {
        val firstDayOfMonth = mainUseCases.getFirstDayOfMonthInMillis(
            state.value.selectedMonth,
            state.value.selectedYear
        )
        val firstDayOfNextMonth = mainUseCases.getFirstDayOfNextMonthInMillis(
            state.value.selectedMonth,
            state.value.selectedYear
        )

        val listOfEvents = mainUseCases.getAllCalendarEventsFromCurrentMonth(
            firstDayOfMonth,
            firstDayOfNextMonth
        )
        _state.value = state.value.copy(
            listOfEvents = listOfEvents
        )
    }

    // TEST DATA
    private fun addTestDataToDatabase() {
        viewModelScope.launch {
            repository.insertCalendarEvent(
                CalendarEventDto(
                    id = 0,
                    startingDate = 1704069142000,
                    endingDate = 1704069142000,
                    isTakingWholeDay = false,
                    isRepeating = false,
                    repeatMode = RepeatMode.EVERY_DAY,
                    title = "Giga Event NN",
                    description = "Event description details",
                    color = 2,
                    reminderMode = ReminderMode.NONE
                )
            )
        }
    }

    private fun setEmptyCalendar(
        selectedMonth: Int
    ) {

        var currentMonth = selectedMonth

        var currentYear = state.value.selectedYear

        val c = Calendar.getInstance()

        if (currentMonth < 0) {
            c.set(Calendar.MONTH, 11)
            currentMonth = 11
            currentYear -= 1

        } else if (currentMonth > 11) {
            c.set(Calendar.MONTH, 0)
            currentMonth = 0
            currentYear += 1
        } else {
            c.set(Calendar.MONTH, currentMonth)
        }

        c.set(Calendar.YEAR, currentYear)
        c.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonthInWeek = c.get(Calendar.DAY_OF_WEEK)
        val daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH)

        var day = 1

        val listOfDays = mutableListOf<CalendarDay>()

        if (firstDayOfMonthInWeek == 1) {
            for (i in 1..6) {
                listOfDays.add(
                    CalendarDay(
                        listOfEvents = emptyList(),
                        isEmpty = true,
                        dayOfMonth = -1
                    )
                )
            }
            listOfDays.add(
                CalendarDay(
                    listOfEvents = emptyList(),
                    isEmpty = false,
                    dayOfMonth = 1
                )
            )
        }

        while (day < daysInMonth + firstDayOfMonthInWeek - 1) {
            listOfDays.add(
                CalendarDay(
                    listOfEvents = emptyList(),
                    isEmpty = (day < firstDayOfMonthInWeek - 1),
                    dayOfMonth = day - firstDayOfMonthInWeek + 2
                )
            )
            day++
        }

        _state.value = state.value.copy(
            selectedMonth = currentMonth,
            selectedYear = currentYear,
            listOfDays = listOfDays,
            listOfEvents = emptyList()
        )
    }

    fun getCurrentDate() {
        val c = Calendar.getInstance()
        val currentMonth = c.get(Calendar.MONTH)
        val currentYear = c.get(Calendar.YEAR)
        val currentDay = c.get(Calendar.DAY_OF_MONTH)

        _state.value = state.value.copy(
            selectedMonth = currentMonth,
            selectedYear = currentYear,
            currentMonth = currentMonth,
            currentYear = currentYear,
            currentDay = currentDay
        )
    }
}

