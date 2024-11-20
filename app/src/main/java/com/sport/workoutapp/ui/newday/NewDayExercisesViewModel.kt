package com.sport.workoutapp.ui.newday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sport.workoutapp.WorkOutApplication.Companion.realm
import com.sport.workoutapp.data.model.Exercise
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class NewDayExercisesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NewDayExercisesUiState())
    val uiState: StateFlow<NewDayExercisesUiState> = _uiState.asStateFlow()

    private var selectedExercises: Int = 0

    init {
        viewModelScope.launch {
            val allExercises = realm.query<Exercise>("type = $0", "Standard").find()
            _uiState.value = NewDayExercisesUiState(
                exercises = allExercises
            )
        }
    }

    private fun updateExercises(exercises: List<Exercise>) {
        _uiState.update { currentState ->
            currentState.copy(
                exercises = exercises
            )
        }
    }

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

data class NewDayExercisesUiState(
    val exercises: List<Exercise> = emptyList()
)