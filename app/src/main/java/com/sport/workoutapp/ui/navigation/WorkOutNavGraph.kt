package com.sport.workoutapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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