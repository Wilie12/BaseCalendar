package com.example.basecalendar.feature_calendar.presentation.add_event_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun AddEventScreenRoot(
    navController: NavController
) {
    val viewModel = hiltViewModel<AddEventViewModel>()

    AddEventScreen(
        state = viewModel.state.value,
        onEvent = {
            viewModel.onEvent(it)
        },
        navController = navController
    )
}