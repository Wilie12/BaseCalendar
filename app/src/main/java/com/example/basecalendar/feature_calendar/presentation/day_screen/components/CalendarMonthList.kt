package com.example.basecalendar.feature_calendar.presentation.day_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basecalendar.R
import com.example.basecalendar.feature_calendar.domain.model.CalendarDay

@Composable
fun CalendarMonthList(
    listOfDays: List<CalendarDay>,
    currentDay: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val dayOfWeek = listOf("Md", "Tu", "Wd", "Th", "Fr", "St", "Sn")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            dayOfWeek.forEach {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            items(listOfDays) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (it.isEmpty) "" else it.dayOfMonth.toString(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background(
                                color = if (it.dayOfMonth == currentDay) MaterialTheme.colorScheme.primary else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable { onClick(it.dayOfMonth) }
                            .fillMaxWidth()
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_color),
                        contentDescription = "Event",
                        tint = if (it.listOfEvents.isNotEmpty()) MaterialTheme.colorScheme.primary else Color.Transparent,
                        modifier = Modifier.size(5.dp)
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun CalendarMonthListPreview() {
    CalendarMonthList(
        listOfDays = emptyList(),
        currentDay = 16,
        onClick = {}
    )
}