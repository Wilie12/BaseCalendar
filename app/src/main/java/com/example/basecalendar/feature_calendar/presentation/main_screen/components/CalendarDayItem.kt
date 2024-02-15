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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basecalendar.feature_calendar.domain.model.CalendarDay

@Composable
fun CalendarDayItem(
    calendarDay: CalendarDay,
    isCurrentDay: Boolean
) {
    if (calendarDay.isEmpty) {
        Text(
            text = "",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
        )
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable {  }
        ) {
            Text(
                text = calendarDay.dayOfMonth.toString(),
                textAlign = TextAlign.Center,
                color = if (isCurrentDay) Color.White else Color.Black,
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
                    calendarDay.listOfEvents.forEach { event ->
                        Text(
                            text = event.title,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Red)
                                .clickable {  }
                        )
                    }
                    for (i in 1..(5 - calendarDay.listOfEvents.size)) {
                        Text(
                            text = "",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            } else {
                Column {
                    for(i in 1..5) {
                        Text(
                            text = "",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}