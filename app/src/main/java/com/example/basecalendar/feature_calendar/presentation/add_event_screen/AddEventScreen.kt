package com.example.basecalendar.feature_calendar.presentation.add_event_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorLong
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.basecalendar.R
import com.example.basecalendar.feature_calendar.data.util.Constants
import com.example.basecalendar.feature_calendar.data.util.ReminderMode
import com.example.basecalendar.feature_calendar.data.util.RepeatMode
import com.example.basecalendar.feature_calendar.presentation.add_event_screen.components.ColorOptionsDialog
import com.example.basecalendar.feature_calendar.presentation.add_event_screen.components.DateText
import com.example.basecalendar.feature_calendar.presentation.add_event_screen.components.RadioOptionsDialog
import com.example.basecalendar.feature_calendar.presentation.add_event_screen.components.TimePickerDialog
import com.example.basecalendar.feature_calendar.presentation.add_event_screen.components.TimeText
import com.example.basecalendar.feature_calendar.util.parseColorIntToString
import com.example.basecalendar.feature_calendar.util.parseReminderModeIntToName
import com.example.basecalendar.feature_calendar.util.parseRepeatModeIntToString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventScreen(
    viewModel: AddEventViewModel = hiltViewModel(),
    navController: NavController
) {
    val scrollState = rememberScrollState()

    val startingDatePickerState = rememberDatePickerState()
    val endingDatePickerState = rememberDatePickerState()
    val startingTimePickerState = rememberTimePickerState()
    val endingTimePickerState = rememberTimePickerState()

    val showStartingDateDialog = rememberSaveable { mutableStateOf(false) }
    val showEndingDateDialog = rememberSaveable { mutableStateOf(false) }
    val showStartingTimeDialog = rememberSaveable { mutableStateOf(false) }
    val showEndingTimeDialog = rememberSaveable { mutableStateOf(false) }

    val showRepeatModeDialog = rememberSaveable { mutableStateOf(false) }
    val showReminderModeDialog = rememberSaveable { mutableStateOf(false) }
    val showColorDialog = rememberSaveable { mutableStateOf(false) }

    startingDatePickerState.setSelection(viewModel.state.value.startingDate)
    endingDatePickerState.setSelection(viewModel.state.value.endingDate)

    if (showStartingDateDialog.value) {
        DatePickerDialog(
            onDismissRequest = { showStartingDateDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    showStartingDateDialog.value = false
                    startingDatePickerState.selectedDateMillis?.let {
                        viewModel.setStateStartingDate(it)
                    }
                }) {
                    Text(text = "Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = { showStartingDateDialog.value = false }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(state = startingDatePickerState)
        }
    }
    if (showEndingDateDialog.value) {
        DatePickerDialog(
            onDismissRequest = { showEndingDateDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    showEndingDateDialog.value = false
                    endingDatePickerState.selectedDateMillis?.let {
                        viewModel.setStateEndingDate(it)
                    }
                }) {
                    Text(text = "Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEndingDateDialog.value = false }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(state = endingDatePickerState)
        }
    }
    if (showStartingTimeDialog.value) {
        TimePickerDialog(
            onCancel = { showStartingTimeDialog.value = false },
            onConfirm = {
                viewModel.setStartingDateHourAndMinutes(
                    hour = startingTimePickerState.hour,
                    minutes = startingTimePickerState.minute
                )
                showStartingTimeDialog.value = false
            }
        ) {
            TimePicker(
                state = startingTimePickerState
            )
        }
    }
    if (showEndingTimeDialog.value) {
        TimePickerDialog(
            onCancel = { showEndingTimeDialog.value = false },
            onConfirm = {
                viewModel.setEndingDateHourAndMinutes(
                    hour = endingTimePickerState.hour,
                    minutes = endingTimePickerState.minute
                )
                showEndingTimeDialog.value = false
            }
        ) {
            TimePicker(
                state = endingTimePickerState
            )
        }
    }
    if (showRepeatModeDialog.value) {
        RadioOptionsDialog(
            radioOptions = RepeatMode.listOfRepeatOptions,
            selectedOption = viewModel.state.value.repeatMode,
            onSelect = {
                viewModel.setStateRepeatMode(it)
                showRepeatModeDialog.value = false
            },
            namingFun = {
                parseRepeatModeIntToString(it)
            },
            onDismiss = { showRepeatModeDialog.value = false }
        )
    }
    if (showReminderModeDialog.value) {
        RadioOptionsDialog(
            radioOptions = ReminderMode.listOfReminderOption,
            selectedOption = viewModel.state.value.reminderMode,
            onSelect = {
                viewModel.setStateReminderMode(it)
                showReminderModeDialog.value = false
            },
            namingFun = {
                parseReminderModeIntToName(it)
            },
            onDismiss = { showReminderModeDialog.value = false }
        )
    }

    if (showColorDialog.value) {
        ColorOptionsDialog(
            radioOptions = Constants.listOfColors,
            selectedOption = viewModel.state.value.color,
            onSelect = {
                viewModel.setStateColor(it)
                showColorDialog.value = false
            },
            namingFun = { parseColorIntToString(it) },
            onDismiss = { showColorDialog.value = false }
        )
    }

    // TODO - finish screen
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
                    TextButton(onClick = {
                        if (viewModel.state.value.title.isNotEmpty() && viewModel.state.value.description.isNotEmpty()) {
                            viewModel.saveEvent()
                        }
                    }) {
                        Text(
                            text = "Save",
                            fontSize = 14.sp
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .scrollable(scrollState, Orientation.Vertical)
        ) {
            TextField(
                value = viewModel.state.value.title,
                onValueChange = {
                    viewModel.setStateTitle(it)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent
                ),
                textStyle = TextStyle(
                    fontSize = 20.sp
                ),
                label = {
                    Text(
                        text = "Add Title",
                        fontSize = 20.sp
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = "Event time"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Whole day")
                }
                Switch(
                    checked = viewModel.state.value.isTakingWholeDay,
                    onCheckedChange = {
                        viewModel.setStateIsTakingWholeDay(it)
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = null,
                        tint = Color.Transparent
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    DateText(date = viewModel.state.value.startingDate) {
                        showStartingDateDialog.value = true
                    }
                }
                AnimatedVisibility(visible = !viewModel.state.value.isTakingWholeDay) {
                    TimeText(date = viewModel.state.value.startingDate) {
                        showStartingTimeDialog.value = true
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = null,
                        tint = Color.Transparent
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    DateText(date = viewModel.state.value.endingDate) {
                        showEndingDateDialog.value = true
                    }
                }
                AnimatedVisibility(visible = !viewModel.state.value.isTakingWholeDay) {
                    TimeText(date = viewModel.state.value.endingDate) {
                        showEndingTimeDialog.value = true
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showRepeatModeDialog.value = true }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_repeat),
                    contentDescription = "Repeat mode"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = parseRepeatModeIntToString(viewModel.state.value.repeatMode)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showReminderModeDialog.value = true }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = "Reminder mode"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = parseReminderModeIntToName(viewModel.state.value.reminderMode)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showColorDialog.value = true }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_color),
                    contentDescription = "Color selection",
                    tint = Color(viewModel.state.value.color)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = parseColorIntToString(viewModel.state.value.color)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_description),
                    contentDescription = "Description"
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = viewModel.state.value.description,
                    onValueChange = {
                        viewModel.setStateDescription(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    label = {
                        Text(
                            text = "Add description"
                        )
                    }
                )
            }
        }
    }
}