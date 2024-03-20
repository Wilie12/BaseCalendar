package com.example.basecalendar.feature_calendar.presentation.day_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import java.util.Calendar

@Composable
fun DayEventItem(
    calendarEventDto: CalendarEventDto,
    currentDay: Int,
    modifier: Modifier = Modifier,
    onEventNavigate: (Int) -> Unit
) {
    // TODO - design event
    val c = Calendar.getInstance()

    c.timeInMillis = calendarEventDto.startingDate
    val startingDay = c.get(Calendar.DAY_OF_MONTH)
    val startingHour = c.get(Calendar.HOUR_OF_DAY)
    val startingMinutes = c.get(Calendar.MINUTE).toFloat().div(60)

    c.timeInMillis = calendarEventDto.endingDate
    val endingDay = c.get(Calendar.DAY_OF_MONTH)
    val endingHour = c.get(Calendar.HOUR_OF_DAY)
    val endingMinutes = c.get(Calendar.MINUTE).toFloat().div(60)

    Box(
        modifier = modifier
            .padding(
                top = if (startingDay < endingDay && endingDay == currentDay) {
                    0.dp
                } else if (startingDay != currentDay && endingDay != currentDay) {
                    0.dp
                } else {
                    (60.dp * (startingHour + startingMinutes))
                }
            )
            .height(
                if (startingDay < endingDay && endingDay == currentDay) {
                    60.dp * (endingHour + endingMinutes)
                } else if (startingDay < endingDay && startingDay == currentDay) {
                    60.dp * (24 - (startingHour + startingMinutes))
                } else if (startingDay != currentDay && endingDay != currentDay) {
                    60.dp * (24)
                } else {
                    60.dp * ((endingHour - (startingHour + startingMinutes)) + endingMinutes)
                }
            )
            .background(
                color = Color(calendarEventDto.color),
                shape = RoundedCornerShape(32.dp)
            )
            .clickable { onEventNavigate(calendarEventDto.id) }
            .padding(8.dp)
    ) {
        Text(text = calendarEventDto.title)
    }
}