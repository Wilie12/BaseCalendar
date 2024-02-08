package com.example.basecalendar.feature_calendar.presentation.main_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.data.util.ReminderMode
import com.example.basecalendar.feature_calendar.data.util.RepeatMode
import com.example.basecalendar.feature_calendar.domain.model.CalendarDay
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {
    // TODO - finish MainViewModel

    private val _testState = mutableStateOf(TestState())
    val testState: State<TestState> = _testState

    val calendarEvent = CalendarEventDto(
        id = 1,
        startingDate = 1L,
        endingDate = 1L,
        isTakingWholeDay = false,
        isRepeating = false,
        repeatMode = RepeatMode.EVERY_DAY,
        title = "Event",
        description = "Event description details",
        color = 2,
        reminderMode = ReminderMode.NONE
    )
    val calendarEvent2 = CalendarEventDto(
        id = 2,
        startingDate = 3L,
        endingDate = 4L,
        isTakingWholeDay = false,
        isRepeating = false,
        repeatMode = RepeatMode.EVERY_DAY,
        title = "Event",
        description = "Event description details",
        color = 2,
        reminderMode = ReminderMode.NONE
    )

    init {
        getCurrentMonthAndYear()
        setEmptyCalendar(testState.value.currentMonth)
    }

    // TODO - do something useful with this
    fun setEmptyCalendar(
        selectedMonth: Int
    ) {

        var currentMonth = selectedMonth
        var currentYear = testState.value.currentYear

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

        Log.d("CURRENT_MONTH", "length: ${listOfDays.size}")
        Log.d("CURRENT_MONTH", "firstDay: $firstDayOfMonthInWeek")

        _testState.value = testState.value.copy(
            currentMonth = currentMonth,
            currentYear = currentYear,
            listOfDays = listOfDays
        )
    }

    private fun getCurrentMonthAndYear() {
        val c = Calendar.getInstance()
        val currentMonth = c.get(Calendar.MONTH)
        val currentYear = c.get(Calendar.YEAR)

        _testState.value = testState.value.copy(
            currentMonth = currentMonth,
            currentYear = currentYear
        )
    }

    fun addTestEvents() {

        val listOfDays = testState.value.listOfDays.toMutableList()

        listOfDays[listOfDays.indexOf(
            listOfDays.find { it.dayOfMonth == 8 }
        )] = listOfDays[listOfDays.indexOf(
            listOfDays.find { it.dayOfMonth == 8 }
        )].copy(listOfEvents = listOf(calendarEvent, calendarEvent2))

        _testState.value = testState.value.copy(listOfDays = listOfDays)
    }
}

