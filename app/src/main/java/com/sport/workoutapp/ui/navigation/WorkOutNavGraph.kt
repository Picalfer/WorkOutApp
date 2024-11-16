package com.sport.workoutapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sport.workoutapp.ui.days.DaysScreen
import com.sport.workoutapp.ui.exercises.ExercisesScreen
import com.sport.workoutapp.ui.newday.NewDayExercisesScreen
import com.sport.workoutapp.ui.splash.SplashScreen
import kotlinx.serialization.Serializable
import org.mongodb.kbson.ObjectId

@Composable
fun WorkOutNavGraph() {
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
            NewDayExercisesScreen()
        }
    }
}

@Serializable
object DaysRoute

@Serializable
object SplashRoute

@Serializable
object NewDayExercisesRoute

@Serializable
data class ExercisesRoute(
    val dayId: String
)