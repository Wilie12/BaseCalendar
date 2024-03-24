package com.example.basecalendar.feature_calendar.presentation.main_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun MainScreenRoot(
    navController: NavController
) {

    val viewModel = hiltViewModel<MainViewModel>()

    MainScreen(
        state = viewModel.state.value,
        onEvent = {
            viewModel.onEvent(it)
        },
        navController = navController
    )
}