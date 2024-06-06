package com.sport.workoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sport.workoutapp.ui.DaysScreen
import com.sport.workoutapp.ui.ExercisesScreen
import com.sport.workoutapp.ui.theme.WorkOutAppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkOutAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = DaysRoute
                ) {
                    composable<DaysRoute> {
                        DaysScreen {
                            navController.navigate(ExercisesRoute(dayNumber = it))
                        }
                    }
                    composable<ExercisesRoute> {
                        val args = it.toRoute<ExercisesRoute>()
                        ExercisesScreen(
                            dayNumber = args.dayNumber
                        )
                    }
                }
            }
        }
    }
}

@Serializable
object DaysRoute

@Serializable
data class ExercisesRoute(
    val dayNumber: Int
)