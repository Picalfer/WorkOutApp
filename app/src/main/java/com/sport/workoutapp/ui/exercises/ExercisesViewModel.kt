package com.sport.workoutapp.ui.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sport.workoutapp.data.getDaysData
import com.sport.workoutapp.data.model.Exercise
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ExercisesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ExercisesUiState())
    val uiState: StateFlow<ExercisesUiState> = _uiState.asStateFlow()

    private var isDayUpdated = false
    private var timerCounter: Deferred<Unit>? = null
    private var doneExercises: Int = 0

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
            currentState.copy(
                exercises = exercises,
                progress = getCurrentProgress()
            )
        }
    }

    private fun getCurrentProgress(): Float {
        val percentage = (doneExercises.toFloat() / uiState.value.exercises.size.toFloat())
        return percentage
    }

    fun changeExerciseDoneStatus(exerciseTitle: String, value: Boolean) {
        val exercises = _uiState.value.exercises.map { exercise ->
            if (exercise.title == exerciseTitle) {
                exercise.copy(isDone = !exercise.isDone)
            } else {
                exercise
            }
        }

        if (value) doneExercises += 1 else doneExercises -= 1
        updateExercises(exercises)
    }

    fun startTimer() {
        stopTimer()

        _uiState.update { currentState ->
            currentState.copy(isTimerNow = true)
        }
        timerCounter = viewModelScope.async(
            CoroutineName("TimerCounter"),
            CoroutineStart.DEFAULT
        )
        {
            for (i in TIMER downTo 1) {
                updateTimer(i)
                delay(1000)
            }
            stopTimer()
        }
    }

    private fun updateTimer(currentTime: Int) {
        _uiState.update { currentState ->
            currentState.copy(timerSeconds = currentTime)
        }
    }

    fun stopTimer() {
        timerCounter?.cancel()

        _uiState.update { currentState ->
            currentState.copy(isTimerNow = false)
        }
    }

    companion object {
        val TIMER = 120 // amount seconds for rest between sets
    }
}

data class ExercisesUiState(
    val currentDayNumber: Int = 0,
    val exercises: List<Exercise> = emptyList(),
    val dayTitle: String = "",
    val isTimerNow: Boolean = false,
    val timerSeconds: Int = ExercisesViewModel.TIMER,
    val progress: Float = 0f,
)
