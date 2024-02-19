package com.example.basecalendar.feature_calendar.presentation.day_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.basecalendar.R
import com.example.basecalendar.feature_calendar.presentation.common.DrawerSheet
import com.example.basecalendar.feature_calendar.util.parseMonthIntToString
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayScreen(
    navController: NavController,
    viewModel: DayViewModel = hiltViewModel()
) {
    
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by remember { mutableStateOf(0) }

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
//                            text = "${parseMonthIntToString(viewModel.state.value.selectedMonth)} " + if (viewModel.state.value.selectedYear != 2024) "${viewModel.state.value.selectedYear}" else ""
                            text = "Day"
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
//                                    viewModel.getCurrentDate()
//                                    viewModel.setFullCalendarForSelectedMonth(
//                                        viewModel.state.value.selectedMonth
//                                    )
                                }
                                .padding(4.dp)
                        ) {
                            Text(
//                                text = viewModel.state.value.currentDay.toString(),
                                text = "1",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(onClick = {
//                            viewModel.setFullCalendarForSelectedMonth(
//                                viewModel.state.value.selectedMonth - 1
//                            )
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Previous Month"
                            )
                        }
                        IconButton(onClick = {
//                            viewModel.setFullCalendarForSelectedMonth(
//                                viewModel.state.value.selectedMonth + 1
//                            )
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_next),
                                contentDescription = "Previous Month"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text(text = "Day")
            }
        }
    }
    
    // TODO
}