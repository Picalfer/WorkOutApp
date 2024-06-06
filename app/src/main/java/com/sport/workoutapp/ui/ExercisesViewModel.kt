package com.sport.workoutapp.ui

import androidx.lifecycle.ViewModel
import com.sport.workoutapp.data.getDaysData
import com.sport.workoutapp.data.model.Exercise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ExercisesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ExercisesUiState())
    val uiState: StateFlow<ExercisesUiState> = _uiState.asStateFlow()

    private var isDayUpdated = false

    fun updateDay(dayNumber: Int) {
        if (!isDayUpdated) {
            val exercises = getDaysData()[dayNumber].exercises
            val dayTitle = getDaysData()[dayNumber].title

            _uiState.value = ExercisesUiState(
                currentDayNumber = dayNumber,
                exercises = exercises,
                dayTitle = dayTitle
            )
            isDayUpdated = true
        }
    }

    private fun updateExercises(exercises: List<Exercise>) {
        _uiState.update { currentState ->
            currentState.copy(exercises = exercises)
        }
    }

    fun changeExerciseIsDone(exerciseTitle: String) {
        val exercises = _uiState.value.exercises.map { exercise ->
            if (exercise.title == exerciseTitle) {
                exercise.copy(isDone = !exercise.isDone)
            } else {
                exercise
            }
        }
        updateExercises(exercises)
    }
}
