package com.example.basecalendar.feature_calendar.presentation.day_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun DayScreenRoot(
    navController: NavController
) {
    val viewModel = hiltViewModel<DayViewModel>()

    DayScreen(
        state = viewModel.state.value,
        onEvent = {
            viewModel.onEvent(it)
        },
        navController = navController
    )
}