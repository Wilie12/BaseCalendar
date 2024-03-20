package com.example.basecalendar.feature_calendar.presentation.day_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.basecalendar.R
import com.example.basecalendar.feature_calendar.presentation.common.DrawerSheet
import com.example.basecalendar.feature_calendar.presentation.day_screen.components.CalendarMonthList
import com.example.basecalendar.feature_calendar.presentation.day_screen.components.DayEventItem
import com.example.basecalendar.feature_calendar.util.navigation.Screen
import com.example.basecalendar.feature_calendar.util.parsers.parseDayOfWeekIntToString
import com.example.basecalendar.feature_calendar.util.parsers.parseMonthIntToString
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayScreen(
    state: DayState,
    onEvent: (DayEvent) -> Unit,
    navController: NavController,
) {

    val scrollState = rememberScrollState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    val isMonthListExpanded = remember { mutableStateOf(false) }

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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                isMonthListExpanded.value = !isMonthListExpanded.value
                            }
                        ) {
                            Text(
                                text = "${parseMonthIntToString(state.selectedDate.month)} " + if (state.selectedDate.year != 2024) "${state.selectedDate.year}" else ""
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_today),
                                contentDescription = if (isMonthListExpanded.value) "Close" else "Expand",
                                modifier = Modifier
                                    .rotate(if (isMonthListExpanded.value) 180f else 0f)
                            )
                        }
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
                                    onEvent(DayEvent.CurrentDay)
                                }
                                .padding(4.dp)
                        ) {
                            Text(
                                text = state.currentDate.day.toString(),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(onClick = {
                            onEvent(DayEvent.PreviousDay)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Previous Month"
                            )
                        }
                        IconButton(onClick = {
                            onEvent(DayEvent.NextDay)
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
                    navController.navigate(
                        Screen.AddEventScreen.route + "/${Screen.DayScreen.route}"
                    )
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            }
        ) { innerPadding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(innerPadding)
                    ) {
                        AnimatedVisibility(visible = isMonthListExpanded.value) {
                            CalendarMonthList(
                                listOfDays = state.listOfDays,
                                currentDay = state.selectedDate.day,
                                onClick = {
                                    onEvent(DayEvent.SetDay(it))
                                },
                            )
                        }
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .width(50.dp)
                            ) {
                                Text(
                                    text = parseDayOfWeekIntToString(state.dayOfWeek),
                                    fontSize = 14.sp,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = state.selectedDate.day.toString(),
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                val listOfWholeDayEvents =
                                    remember(state.listOfEventsFromCurrentDay) {
                                        state.listOfEventsFromCurrentDay.filter { it.isTakingWholeDay }
                                    }

                                listOfWholeDayEvents.forEach { calendarEvent ->
                                    Text(
                                        text = calendarEvent.title,
                                        fontSize = 14.sp,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                shape = RoundedCornerShape(32.dp),
                                                color = Color(calendarEvent.color)
                                            )
                                    )
                                }
                            }
                        }
                        Divider()
                    }
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .verticalScroll(scrollState)
                            .padding(horizontal = 8.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Column {
                                for (i in 1..23) {
                                    if (i == 1) {
                                        Spacer(modifier = Modifier.height(30.dp))
                                    }
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .height(60.dp)
                                    ) {
                                        Text(
                                            text = if (i < 10) "0$i:00" else "$i:00",
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.width(50.dp),
                                            fontSize = 14.sp
                                        )
                                        Divider()
                                    }
                                    if (i == 23) {
                                        Spacer(modifier = Modifier.height(30.dp))
                                    }
                                }
                            }
                            Row {
                                Box(
                                    modifier = Modifier
                                        .width(50.dp)
                                )
                                state.listOfEventsFromCurrentDay.forEach {
                                    if (!(it.isTakingWholeDay)) {
                                        DayEventItem(
                                            calendarEventDto = it,
                                            currentDay = state.selectedDate.day,
                                            modifier = Modifier.weight(1f),
                                            onEventNavigate = { eventId ->
                                                navController.navigate(Screen.EventScreen.route + "/$eventId/${Screen.DayScreen.route}")
                                            }
                                        )
                                    }
                                }
                            }
                            if (state.selectedDate == state.currentDate) {
                                Divider(
                                    color = MaterialTheme.colorScheme.primary,
                                    thickness = 2.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 50.dp,
                                            top = (60.dp * (state.currentHour + state.currentMinutes.toFloat().div(60)))
                                        )
                                )
                            }
                        }
                    }
                }
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .offset(
                            x = 58.dp,
                            y = if (isMonthListExpanded.value) 250.dp else 0.dp
                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun DayScreenPreview() {
    DayScreen(
        state = DayState(),
        onEvent = {},
        navController = rememberNavController()
    )
}