package com.sport.workoutapp.ui.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sport.workoutapp.WorkOutApplication.Companion.player
import com.sport.workoutapp.WorkOutApplication.Companion.realm
import com.sport.workoutapp.data.model.Day
import com.sport.workoutapp.data.model.Exercise
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class ExercisesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ExercisesUiState())
    val uiState: StateFlow<ExercisesUiState> = _uiState.asStateFlow()

    private var isDayUpdated = false
    private var timerCounter: Deferred<Unit>? = null
    private var doneExercises: Int = 0

    fun updateDay(dayId: ObjectId) {
        if (!isDayUpdated) {
            viewModelScope.launch {
                val day = realm.query<Day>("_id == $0", dayId).first().find()
                if (day != null) {
                    val exercises = day.exercises
                    val dayTitle = day.title

                    _uiState.value = ExercisesUiState(
                        currentDayId = dayId,
                        exercises = exercises,
                        dayTitle = dayTitle
                    )
                    isDayUpdated = true
                }
            }

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

    fun changeExerciseDoneStatus(exerciseId: ObjectId, value: Boolean) {
        val exercises = _uiState.value.exercises.map { exercise ->
            if (exercise._id == exerciseId) {
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
            stopTimer(true)
        }
    }

    private fun updateTimer(currentTime: Int) {
        _uiState.update { currentState ->
            currentState.copy(timerSeconds = currentTime)
        }
    }

    fun stopTimer(withGong: Boolean = false) {
        timerCounter?.cancel()

        _uiState.update { currentState ->
            currentState.copy(isTimerNow = false)
        }
        if (withGong) {
            player.playGong()
        }
    }

    companion object {
        val TIMER = 120 // amount seconds for rest between sets
    }
}

data class ExercisesUiState(
    val currentDayId: ObjectId? = null,
    val exercises: List<Exercise> = emptyList(),
    val dayTitle: String = "",
    val isTimerNow: Boolean = false,
    val timerSeconds: Int = ExercisesViewModel.TIMER,
    val progress: Float = 0f,
)
