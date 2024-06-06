package com.sport.workoutapp.ui

import androidx.lifecycle.ViewModel
import com.sport.workoutapp.data.getDaysData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExercisesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ExercisesUiState())
    val uiState: StateFlow<ExercisesUiState> = _uiState.asStateFlow()

    init {
        updateExercises()
    }

    fun updateExercises(dayNumber: Int = 0) {

        val exercises = getDaysData()[dayNumber].exercises
        val dayTitle = getDaysData()[dayNumber].title

        _uiState.value = ExercisesUiState(
            currentDayNumber = dayNumber,
            exercises = exercises,
            dayTitle = dayTitle
        )
    }
}