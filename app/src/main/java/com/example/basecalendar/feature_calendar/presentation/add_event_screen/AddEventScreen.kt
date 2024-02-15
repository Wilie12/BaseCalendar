package com.example.basecalendar.feature_calendar.presentation.add_event_screen

import android.app.TimePickerDialog
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.TimePickerLayoutType
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.basecalendar.R
import com.example.basecalendar.feature_calendar.presentation.add_event_screen.components.DateText
import com.example.basecalendar.feature_calendar.presentation.add_event_screen.components.TimePickerDialog
import com.example.basecalendar.feature_calendar.presentation.add_event_screen.components.TimeText
import java.util.Calendar

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
                    Button(onClick = { /*TODO*/ }) {
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
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_repeat),
                    contentDescription = "Repeat mode"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Don't repeat")
            }
        }
    }
}