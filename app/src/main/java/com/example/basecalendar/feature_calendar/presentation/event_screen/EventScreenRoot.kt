package com.example.basecalendar.feature_calendar.presentation.event_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

// TODO
@Composable
fun EventScreenRoot(
    navController: NavController
) {
    val viewModel = hiltViewModel<EventViewModel>()

    EventScreen(
        state = viewModel.state.value,
        onEvent = {
            viewModel.onEvent(it)
        },
        navController = navController
    )
}