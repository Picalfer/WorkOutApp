package com.sport.workoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sport.workoutapp.ui.Navigation
import com.sport.workoutapp.ui.theme.WorkOutAppTheme

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