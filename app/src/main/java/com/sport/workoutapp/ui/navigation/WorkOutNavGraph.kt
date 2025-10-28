package com.sport.workoutapp.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sport.workoutapp.ui.calendar.CalendarScreen
import com.sport.workoutapp.ui.days.DaysScreen
import com.sport.workoutapp.ui.exercises.ExercisesScreen
import com.sport.workoutapp.ui.newday.addons.NewDayAddonsScreen
import com.sport.workoutapp.ui.newday.exercises.NewDayExercisesScreen
import com.sport.workoutapp.ui.splash.SplashScreen
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import org.mongodb.kbson.ObjectId

// TODO refactor this to general one style, now its two styles to do routes

@Serializable
object DaysRoute

@Serializable
object SplashRoute

@Serializable
data class ExercisesRoute(
    val dayId: String,
)

@Serializable
object NewDayExercisesRoute

@Serializable
data class NewDayAddonsRoute(
    val exercises: String,
)

sealed class NavDestination(val title: String, val route: String, val icon: ImageVector) {
    object Home : NavDestination(title = "Home", route = "home_screen", icon = Icons.Filled.Home)
    object Episodes :
        NavDestination(title = "Episodes", route = "episodes_screen", icon = Icons.Filled.PlayArrow)
    object Calendar :
        NavDestination(title = "Calendar", route = "calendar_screen", icon = Icons.Filled.DateRange)
}

@Composable
fun WorkOutNavGraph() {
    val navController = rememberNavController()
    val items = listOf(NavDestination.Home, NavDestination.Episodes, NavDestination.Calendar)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SplashRoute,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = NavDestination.Episodes.route) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Episodes", fontSize = 62.sp, color = Color.Gray)
                }
            }
            composable(route = NavDestination.Calendar.route) {
                CalendarScreen()
            }
            composable(route = NavDestination.Home.route) {
                DaysScreen(
                    onDayClick = {
                        navController.navigate(ExercisesRoute(dayId = it.toHexString()))
                    },
                    onBtnAddDayClick = {
                        navController.navigate(NewDayExercisesRoute)
                    }
                )
            }
            composable<SplashRoute> {
                SplashScreen(onFinished = {
                    navController.navigate(DaysRoute)
                })
            }
            composable<DaysRoute> {
                DaysScreen(
                    onDayClick = {
                        navController.navigate(ExercisesRoute(dayId = it.toHexString()))
                    },
                    onBtnAddDayClick = {
                        navController.navigate(NewDayExercisesRoute)
                    }
                )
            }
            composable<ExercisesRoute> {
                val args = it.toRoute<ExercisesRoute>()
                val dayId = ObjectId(args.dayId)
                ExercisesScreen(
                    dayId = dayId
                )
            }
            composable<NewDayExercisesRoute> {
                NewDayExercisesScreen(
                    onBackClick = {
                        navController.navigate(DaysRoute)
                    },
                    onNextClick = {
                        navController.navigate(NewDayAddonsRoute(exercises = it))
                    }
                )
            }
            composable<NewDayAddonsRoute> {
                val args = it.toRoute<NewDayAddonsRoute>()
                val exercisesJson = Json.parseToJsonElement(args.exercises).jsonObject
                val exercises: List<ObjectId> =
                    Json.decodeFromJsonElement<List<ObjectId>>(exercisesJson["exercises"]!!)

                NewDayAddonsScreen(
                    onBackClick = {
                        navController.navigate(NewDayExercisesRoute)
                    },
                    onExitClick = {
                        navController.navigate(DaysRoute)
                    },
                    onNextClick = {

                    },
                    exercises = exercises
                )
            }
        }
    }

    /*Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = SplashRoute
        ) {
            composable<SplashRoute> {
                SplashScreen(onFinished = {
                    navController.navigate(DaysRoute)
                })
            }
            composable<DaysRoute> {
                DaysScreen(
                    onDayClick = {
                        navController.navigate(ExercisesRoute(dayId = it.toHexString()))
                    },
                    onBtnAddDayClick = {
                        navController.navigate(NewDayExercisesRoute)
                    }
                )
            }
            composable<ExercisesRoute> {
                val args = it.toRoute<ExercisesRoute>()
                val dayId = ObjectId(args.dayId)
                ExercisesScreen(
                    dayId = dayId
                )
            }
            composable<NewDayExercisesRoute> {
                NewDayExercisesScreen(
                    onBackClick = {
                        navController.navigate(DaysRoute)
                    },
                    onNextClick = {
                        navController.navigate(NewDayAddonsRoute(exercises = it))
                    }
                )
            }
            composable<NewDayAddonsRoute> {
                val args = it.toRoute<NewDayAddonsRoute>()
                val exercisesJson = Json.parseToJsonElement(args.exercises).jsonObject
                val exercises: List<ObjectId> =
                    Json.decodeFromJsonElement<List<ObjectId>>(exercisesJson["exercises"]!!)

                NewDayAddonsScreen(
                    onBackClick = {
                        navController.navigate(NewDayExercisesRoute)
                    },
                    onExitClick = {
                        navController.navigate(DaysRoute)
                    },
                    onNextClick = {

                    },
                    exercises = exercises
                )
            }
        }
    }*/
}