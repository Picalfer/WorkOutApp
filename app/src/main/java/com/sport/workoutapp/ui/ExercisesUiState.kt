package com.sport.workoutapp.ui

import com.sport.workoutapp.data.model.Exercise

data class ExercisesUiState(
    val currentDayNumber: Int = 0,
    val exercises: List<Exercise> = emptyList(),
    val dayTitle: String = "",
    val isTimerNow: Boolean = false,
    val timerSeconds: Int = ExercisesViewModel.TIMER,
    val progress: Float = 0f,
)