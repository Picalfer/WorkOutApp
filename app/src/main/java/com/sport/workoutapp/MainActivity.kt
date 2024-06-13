package com.sport.workoutapp

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sport.workoutapp.ui.DaysScreen
import com.sport.workoutapp.ui.ExercisesScreen
import com.sport.workoutapp.ui.Navigation
import com.sport.workoutapp.ui.theme.WorkOutAppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkOutAppTheme {
                Navigation()
            }
        }
    }
}

