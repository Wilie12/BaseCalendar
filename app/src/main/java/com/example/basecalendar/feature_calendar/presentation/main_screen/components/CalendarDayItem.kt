package com.example.basecalendar.feature_calendar.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basecalendar.feature_calendar.domain.model.CalendarDay

@Composable
fun CalendarDayItem(
    calendarDay: CalendarDay,
    isCurrentDay: Boolean,
    onDayNavigate: (Int) -> Unit,
    onEventNavigate: (Int) -> Unit
) {
    if (calendarDay.isEmpty) {

        Column {
            Text(
                text = "",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            for (i in 1..5) {
                Text(
                    text = "",
                    fontSize = 14.sp
                )
            }
        }
    } else {
        Column(
            modifier = Modifier.clickable {
                onDayNavigate(calendarDay.dayOfMonth)
            }
        ) {
            Text(
                text = calendarDay.dayOfMonth.toString(),
                textAlign = TextAlign.Center,
                color = if (isCurrentDay) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(
                        color = if (isCurrentDay) MaterialTheme.colorScheme.primary else Color.Transparent,
                        shape = CircleShape
                    )
            )
            if (calendarDay.listOfEvents.isNotEmpty()) {
                Column {
                    calendarDay.listOfEvents.forEachIndexed { index, event ->
                        if (index < 5) {
                            if (index != 4) {
                                Text(
                                    text = event.title,
                                    maxLines = 1,
                                    textAlign = TextAlign.Center,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = Color(event.color),
                                            shape = CircleShape
                                        )
                                        .clickable {
                                            onEventNavigate(event.id)
                                        }
                                )
                            } else {
                                if (calendarDay.listOfEvents.size > 5) {
                                    Text(
                                        text = "...",
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                } else {
                                    Text(
                                        text = event.title,
                                        maxLines = 1,
                                        textAlign = TextAlign.Center,
                                        fontSize = 14.sp,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                color = Color(event.color),
                                                shape = CircleShape
                                            )
                                            .clickable {
                                                onEventNavigate(event.id)
                                            }
                                    )
                                }
                            }
                        }
                    }
                    for (i in 1..(5 - calendarDay.listOfEvents.size)) {
                        Text(
                            text = "",
                            fontSize = 14.sp
                        )
                    }
                }
            } else {
                Column {
                    for (i in 1..5) {
                        Text(
                            text = "",
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}