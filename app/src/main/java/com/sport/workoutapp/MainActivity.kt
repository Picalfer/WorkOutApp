package com.sport.workoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sport.workoutapp.ui.composable.ExercisesScreen
import com.sport.workoutapp.ui.theme.WorkOutAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkOutAppTheme {
                ExercisesScreen()
            }
        }
    }
}