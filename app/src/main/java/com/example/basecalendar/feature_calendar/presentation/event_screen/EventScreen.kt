package com.example.basecalendar.feature_calendar.presentation.event_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.basecalendar.R
import com.example.basecalendar.feature_calendar.presentation.add_event_screen.components.DateText
import com.example.basecalendar.feature_calendar.presentation.add_event_screen.components.TimeText
import com.example.basecalendar.feature_calendar.util.parsers.parseReminderModeIntToName
import com.example.basecalendar.feature_calendar.util.parsers.parseRepeatModeIntToString

// TODO
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(
    state: EventState,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cancel),
                            contentDescription = "Cancel"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // TODO - navigate to AddEventScreen
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_edit),
                            contentDescription = "Edit"
                        )
                    }
                    IconButton(onClick = {
                        // TODO - display options
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_more),
                            contentDescription = "More options"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            // TODO - design screen
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_color),
                    contentDescription = "Color",
                    tint = Color(state.event.color)
                )
                Column {
                    Text(text = state.event.title)
                    Row {
                        DateText(date = state.event.startingDate) {}
                        TimeText(date = state.event.startingDate) {}
                    }
                    Row {
                        DateText(date = state.event.endingDate) {}
                        TimeText(date = state.event.endingDate) {}
                    }
                    Text(text = "Repeated: ${parseRepeatModeIntToString(state.event.repeatMode)}")
                }
            }
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = "Reminder"
                )
                Text(text = parseReminderModeIntToName(state.event.reminderMode))
            }
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_description),
                    contentDescription = "Description"
                )
                Text(text = state.event.description)
            }
        }
    }
}

@Preview
@Composable
fun EventScreenPreview() {
    EventScreen(
        state = EventState(),
        navController = rememberNavController()
    )
}