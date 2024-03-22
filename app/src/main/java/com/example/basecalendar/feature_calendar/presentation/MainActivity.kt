package com.example.basecalendar.feature_calendar.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.basecalendar.feature_calendar.presentation.add_event_screen.AddEventScreenRoot
import com.example.basecalendar.feature_calendar.presentation.day_screen.DayScreenRoot
import com.example.basecalendar.feature_calendar.presentation.event_screen.EventScreenRoot
import com.example.basecalendar.feature_calendar.presentation.main_screen.MainScreenRoot
import com.example.basecalendar.feature_calendar.util.navigation.Screen
import com.example.basecalendar.ui.theme.BaseCalendarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO - rebuild whole app to make navigationDrawer in MainActivity
        // TODO - think about adding tasks database
        setContent {
            BaseCalendarTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {
                        composable(route = Screen.MainScreen.route) {
                            MainScreenRoot(navController = navController)
                        }
                        composable(
                            route = Screen.AddEventScreen.route + "/{screen}?eventId={eventId}&isDuplicate={isDuplicate}",
                            arguments = listOf(
                                navArgument(name = "screen") {
                                    type = NavType.StringType
                                },
                                navArgument(name = "eventId") {
                                    type = NavType.IntType
                                    defaultValue = 0
                                },
                                navArgument(name = "isDuplicate") {
                                    type = NavType.BoolType
                                    defaultValue = false
                                }
                            )
                        ) {
                            AddEventScreenRoot(navController = navController)
                        }
                        composable(
                            route = Screen.DayScreen.route + "?day={day}",
                            arguments = listOf(
                                navArgument(name = "day") {
                                    type = NavType.IntType
                                    defaultValue = 0
                                }
                            )
                        ) {
                            DayScreenRoot(navController = navController)
                        }
                        composable(
                            route = Screen.EventScreen.route + "/{eventId}/{screen}",
                            arguments = listOf(
                                navArgument(name = "eventId") {
                                    type = NavType.IntType
                                },
                                navArgument(name = "screen") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            EventScreenRoot(navController = navController)
                        }
                    }
                }
            }
        }

    }
}