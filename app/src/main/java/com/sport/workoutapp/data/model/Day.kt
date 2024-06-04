package com.sport.workoutapp.data.model

import androidx.compose.ui.graphics.Color

data class Day(
    val title: String,
    val color: Color,
    val exercises: List<String>
)