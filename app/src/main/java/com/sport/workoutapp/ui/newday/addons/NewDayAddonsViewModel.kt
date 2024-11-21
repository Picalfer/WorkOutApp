package com.sport.workoutapp.ui.newday.addons

import androidx.lifecycle.ViewModel
import com.sport.workoutapp.data.model.Exercise
import com.sport.workoutapp.data.model.ExerciseType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.mongodb.kbson.ObjectId

class NewDayAddonsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NewDayAddonsUiState())
    val uiState: StateFlow<NewDayAddonsUiState> = _uiState.asStateFlow()

    private var selectedExercises: Int = 0

    init {
        val warmUp = Exercise().apply {
            type = ExerciseType.WarmUp.name
            title = "Добавить разминку"
        }
        val warmDown = Exercise().apply {
            type = ExerciseType.WarmDown.name
            title = "Добавить заминку"
        }

        updateExercises(listOf(warmUp, warmDown))
    }

    // TODO эта функция уже в трех viewModel, надо зарефакторить
    private fun updateExercises(exercises: List<Exercise>) {
        _uiState.update { currentState ->
            currentState.copy(
                exercises = exercises
            )
        }
    }

    // TODO эта функция уже в трех viewModel, надо зарефакторить
    fun changeExerciseDoneStatus(exerciseId: ObjectId, value: Boolean) {
        val exercises = _uiState.value.exercises.map { exercise ->
            if (exercise._id == exerciseId) {
                exercise.copy(isDone = !exercise.isDone)
            } else {
                exercise
            }
        }

        if (value) selectedExercises += 1 else selectedExercises -= 1
        updateExercises(exercises)
    }
}

data class NewDayAddonsUiState(
    val exercises: List<Exercise> = emptyList(),
)