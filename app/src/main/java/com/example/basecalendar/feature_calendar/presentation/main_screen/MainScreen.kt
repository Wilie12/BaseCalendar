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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.basecalendar.R
import com.example.basecalendar.feature_calendar.presentation.common.DrawerSheet
import com.example.basecalendar.feature_calendar.presentation.main_screen.components.CalendarDayItem
import com.example.basecalendar.feature_calendar.util.Screen
import com.example.basecalendar.feature_calendar.util.parseMonthIntToString
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: MainState,
    onEvent: (MainEvent) -> Unit,
    navController: NavController
) {
    val dayOfWeek = listOf("Md", "Tu", "Wd", "Th", "Fr", "St", "Sn")

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by remember { mutableIntStateOf(1) }

    ModalNavigationDrawer(
        drawerContent = {
            DrawerSheet(
                navController = navController,
                selectedItemIndex = selectedItemIndex,
                scope = scope,
                state = drawerState
            ) {
                selectedItemIndex = it
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "${parseMonthIntToString(state.selectedDate.month)} " + if (state.selectedDate.year != 2024) "${state.selectedDate.year}" else ""
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
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
                                    onEvent(MainEvent.CurrentMonth)
                                }
                                .padding(4.dp)
                        ) {
                            Text(
                                text = state.currentDate.day.toString(),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(onClick = {
                            onEvent(MainEvent.PreviousMonth)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Previous Month"
                            )
                        }
                        IconButton(onClick = {
                            onEvent(MainEvent.NextMonth)
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
                Box(modifier = Modifier.fillMaxWidth()) {
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
                    if (state.isLoading) {
                        LinearProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                        )
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
                AnimatedVisibility(visible = !state.isLoading) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(7),
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        items(state.listOfDays) {
                            CalendarDayItem(
                                calendarDay = it,
                                isCurrentDay = it.dayOfMonth == state.currentDate.day &&
                                        state.currentDate.year == state.selectedDate.year &&
                                        state.currentDate.month == state.selectedDate.month
                            )
                            Divider(modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        state = MainState(),
        onEvent = {},
        navController = rememberNavController()
    )
}