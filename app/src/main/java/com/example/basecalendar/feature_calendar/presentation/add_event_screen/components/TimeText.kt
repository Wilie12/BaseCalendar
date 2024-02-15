package com.example.basecalendar.feature_calendar.presentation.add_event_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TimeText(
    date: Long,
    onClick: () -> Unit
) {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())

    Text(
        text = sdf.format(date),
        modifier = Modifier.clickable { onClick() }
    )
}