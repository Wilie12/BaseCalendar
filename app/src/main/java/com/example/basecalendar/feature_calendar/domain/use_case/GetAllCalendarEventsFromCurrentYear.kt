package com.example.basecalendar.feature_calendar.domain.use_case

import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.data.util.RepeatMode
import com.example.basecalendar.feature_calendar.domain.repository.CalendarRepository

class GetAllCalendarEventsFromCurrentYear(
    private val repository: CalendarRepository
) {
    suspend operator fun invoke(
        firstDayOfYear: Long,
        firstDayOfNextYear: Long
    ): List<CalendarEventDto> {

        val listOfEvents = repository.getAllCalendarEvents()
        val completeListOfEvents = listOfEvents.toMutableList()

        listOfEvents.forEach { calendarEventDto ->
            if (calendarEventDto.isRepeating) {
                when (calendarEventDto.repeatMode) {
                    RepeatMode.EVERY_DAY -> {
                        var startDate = calendarEventDto.startingDate
                        var endDate = calendarEventDto.endingDate
                        while (startDate <= firstDayOfNextYear) {
                            completeListOfEvents.add(
                                CalendarEventDto(
                                    id = calendarEventDto.id,
                                    startingDate = startDate + 86400000,
                                    endingDate = endDate + 86400000,
                                    isTakingWholeDay = calendarEventDto.isTakingWholeDay,
                                    isRepeating = calendarEventDto.isRepeating,
                                    repeatMode = calendarEventDto.repeatMode,
                                    title = calendarEventDto.title,
                                    description = calendarEventDto.description,
                                    color = calendarEventDto.color,
                                    reminderMode = calendarEventDto.reminderMode
                                )
                            )
                            startDate += 86400000
                            endDate += 86400000
                        }
                    }
                    RepeatMode.EVERY_WEEK -> {
                        var startDate = calendarEventDto.startingDate
                        var endDate = calendarEventDto.endingDate
                        while (startDate <= firstDayOfNextYear) {
                            completeListOfEvents.add(
                                CalendarEventDto(
                                    id = calendarEventDto.id,
                                    startingDate = startDate + 604800000,
                                    endingDate = endDate + 604800000,
                                    isTakingWholeDay = calendarEventDto.isTakingWholeDay,
                                    isRepeating = calendarEventDto.isRepeating,
                                    repeatMode = calendarEventDto.repeatMode,
                                    title = calendarEventDto.title,
                                    description = calendarEventDto.description,
                                    color = calendarEventDto.color,
                                    reminderMode = calendarEventDto.reminderMode
                                )
                            )
                            startDate += 604800000
                            endDate += 604800000
                        }
                    }
                    RepeatMode.EVERY_MONTH -> {
                        var startDate = calendarEventDto.startingDate
                        var endDate = calendarEventDto.endingDate
                        while (startDate <= firstDayOfNextYear) {
                            completeListOfEvents.add(
                                CalendarEventDto(
                                    id = calendarEventDto.id,
                                    startingDate = startDate + 2629746000,
                                    endingDate = endDate + 2629746000,
                                    isTakingWholeDay = calendarEventDto.isTakingWholeDay,
                                    isRepeating = calendarEventDto.isRepeating,
                                    repeatMode = calendarEventDto.repeatMode,
                                    title = calendarEventDto.title,
                                    description = calendarEventDto.description,
                                    color = calendarEventDto.color,
                                    reminderMode = calendarEventDto.reminderMode
                                )
                            )
                            startDate += 2629746000
                            endDate += 2629746000
                        }
                    }
                    RepeatMode.EVERY_YEAR -> {
                        var startDate = calendarEventDto.startingDate
                        var endDate = calendarEventDto.endingDate
                        while (startDate <= firstDayOfNextYear) {
                            completeListOfEvents.add(
                                CalendarEventDto(
                                    id = calendarEventDto.id,
                                    startingDate = startDate + 31556952000,
                                    endingDate = endDate + 31556952000,
                                    isTakingWholeDay = calendarEventDto.isTakingWholeDay,
                                    isRepeating = calendarEventDto.isRepeating,
                                    repeatMode = calendarEventDto.repeatMode,
                                    title = calendarEventDto.title,
                                    description = calendarEventDto.description,
                                    color = calendarEventDto.color,
                                    reminderMode = calendarEventDto.reminderMode
                                )
                            )
                            startDate += 31556952000
                            endDate += 31556952000
                        }
                    }
                }
            }
        }

        return completeListOfEvents.toList().filter { calendarEvent ->
            calendarEvent.startingDate >= firstDayOfYear || calendarEvent.endingDate <= firstDayOfNextYear
        }
    }
}