package com.sport.workoutapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Composable
fun Navigation() {
    val navController = rememberNavController()

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
            DaysScreen(onDayClick = {
                navController.navigate(ExercisesRoute(dayNumber = it))
            })
        }
        composable<ExercisesRoute> {
            val args = it.toRoute<ExercisesRoute>()
            ExercisesScreen(
                dayNumber = args.dayNumber
            )
        }
    }
}

@Serializable
object DaysRoute

@Serializable
object SplashRoute

@Serializable
data class ExercisesRoute(
    val dayNumber: Int
)