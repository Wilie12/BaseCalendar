package com.example.basecalendar.feature_calendar.presentation.main_screen

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.basecalendar.R
import com.example.basecalendar.feature_calendar.util.parseMonthIntToString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    val dayOfWeek = listOf("Md", "Tu", "Wd", "Th", "Fr", "St", "Sn")


    // TODO - finish main screen
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "${parseMonthIntToString(viewModel.testState.value.currentMonth)} " + if (viewModel.testState.value.currentYear != 2024) "${viewModel.testState.value.currentYear}" else ""
                    )
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.setFullCalendarForCurrentMonth(
                            viewModel.testState.value.currentMonth - 1
                        )
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Previous Month"
                        )
                    }
                    IconButton(onClick = {
                        viewModel.setFullCalendarForCurrentMonth(
                            viewModel.testState.value.currentMonth + 1
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
            FloatingActionButton(onClick = {}) {
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
            Box() {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(viewModel.testState.value.listOfDays) {
                        if (it.isEmpty) {
                            Text(
                                text = "",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        } else {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = it.dayOfMonth.toString(),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                )
                                if (it.listOfEvents.isNotEmpty()) {
                                    Column {
                                        it.listOfEvents.forEach { event ->
                                            Text(
                                                text = event.id.toString(),
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(Color.Red)
                                            )
                                        }
                                    }
                                } else {
                                    Column {
                                        for(i in 1..5) {
                                            Text(
                                                text = "",
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Divider(modifier = Modifier.fillMaxWidth())
                    }
                }
                if (viewModel.testState.value.isLoading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}