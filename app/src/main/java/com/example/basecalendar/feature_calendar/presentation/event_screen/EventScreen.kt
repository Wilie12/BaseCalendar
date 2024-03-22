package com.example.basecalendar.feature_calendar.presentation.event_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.basecalendar.R
import com.example.basecalendar.feature_calendar.data.local_data_source.dto.CalendarEventDto
import com.example.basecalendar.feature_calendar.data.util.Constants
import com.example.basecalendar.feature_calendar.data.util.ReminderMode
import com.example.basecalendar.feature_calendar.data.util.RepeatMode
import com.example.basecalendar.feature_calendar.presentation.add_event_screen.components.DateText
import com.example.basecalendar.feature_calendar.presentation.add_event_screen.components.TimeText
import com.example.basecalendar.feature_calendar.util.navigation.Screen
import com.example.basecalendar.feature_calendar.util.parsers.parseReminderModeIntToName
import com.example.basecalendar.feature_calendar.util.parsers.parseRepeatModeIntToString

// TODO
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(
    state: EventState,
    onEvent: (EventEvent) -> Unit,
    navController: NavController
) {

    val showMenu = remember { mutableStateOf(false) }

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
                        navController.navigate(Screen.AddEventScreen.route + "/${state.screenRoute}?eventId=${state.event.id}")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_edit),
                            contentDescription = "Edit"
                        )
                    }
                    Box {
                        IconButton(onClick = {
                            showMenu.value = !showMenu.value
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_more),
                                contentDescription = "More options"
                            )
                        }
                        DropdownMenu(
                            expanded = showMenu.value,
                            onDismissRequest = { showMenu.value = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(text = "Delete")
                                },
                                onClick = {
                                    onEvent(EventEvent.DeleteEvent)
                                    navController.navigate(state.screenRoute) {
                                        popUpTo(state.screenRoute) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Duplicate") },
                                onClick = {
                                    navController.navigate(Screen.AddEventScreen.route + "/${state.screenRoute}?eventId=${state.event.id}&isDuplicate=${true}")
                                })
                        }
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
            if (!state.isLoading) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_color),
                        contentDescription = "Color",
                        tint = Color(state.event.color)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = state.event.title,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = null,
                        tint = Color.Transparent
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Row {
                            DateText(date = state.event.startingDate) {}
                            Spacer(modifier = Modifier.width(8.dp))
                            TimeText(date = state.event.startingDate) {}
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row {
                            DateText(date = state.event.endingDate) {}
                            Spacer(modifier = Modifier.width(8.dp))
                            TimeText(date = state.event.endingDate) {}
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Repeated: ${parseRepeatModeIntToString(state.event.repeatMode)}")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "Reminder"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = parseReminderModeIntToName(state.event.reminderMode))
                }
                if (state.event.description.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_description),
                            contentDescription = "Description"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = state.event.description)
                    }
                }
            } else {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
fun EventScreenPreview() {
    EventScreen(
        state = EventState(
            event = CalendarEventDto(
                id = 0,
                startingDate = 1691835780000,
                endingDate = 1691842980000L,
                isTakingWholeDay = false,
                isRepeating = false,
                repeatMode = RepeatMode.EVERY_WEEK,
                title = "Title",
                description = "Description",
                color = Constants.blue,
                reminderMode = ReminderMode.HOUR_1
            )
        ),
        onEvent = {},
        navController = rememberNavController()
    )
}