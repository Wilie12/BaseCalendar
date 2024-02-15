package com.example.basecalendar.feature_calendar.presentation.main_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.basecalendar.R
import com.example.basecalendar.feature_calendar.presentation.main_screen.components.CalendarDayItem
import com.example.basecalendar.feature_calendar.util.Screen
import com.example.basecalendar.feature_calendar.util.parseMonthIntToString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {

    val dayOfWeek = listOf("Md", "Tu", "Wd", "Th", "Fr", "St", "Sn")

    // TODO - finish main screen
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "${parseMonthIntToString(viewModel.state.value.selectedMonth)} " + if (viewModel.state.value.selectedYear != 2024) "${viewModel.state.value.selectedYear}" else ""
                    )
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                viewModel.getCurrentDate()
                                viewModel.setFullCalendarForSelectedMonth(
                                    viewModel.state.value.selectedMonth
                                )
                            }
                            .padding(4.dp)
                    ) {
                        Text(
                            text = viewModel.state.value.currentDay.toString(),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = {
                        viewModel.setFullCalendarForSelectedMonth(
                            viewModel.state.value.selectedMonth - 1
                        )
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Previous Month"
                        )
                    }
                    IconButton(onClick = {
                        viewModel.setFullCalendarForSelectedMonth(
                            viewModel.state.value.selectedMonth + 1
                        )
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_next),
                            contentDescription = "Previous Month"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddEventScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                dayOfWeek.forEach {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                    )
                }
            }
            Divider(modifier = Modifier.fillMaxWidth())
            if (viewModel.state.value.isLoading) {
                AnimatedVisibility(visible = viewModel.state.value.isLoading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }
            AnimatedVisibility(visible = !viewModel.state.value.isLoading) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(viewModel.state.value.listOfDays) {
                        CalendarDayItem(
                            calendarDay = it,
                            isCurrentDay = it.dayOfMonth == viewModel.state.value.currentDay &&
                                    viewModel.state.value.currentYear == viewModel.state.value.selectedYear &&
                                    viewModel.state.value.currentMonth == viewModel.state.value.selectedMonth
                        )
                        Divider(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}