package com.sport.workoutapp.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sport.workoutapp.WorkOutApplication.Companion.realm
import com.sport.workoutapp.data.TrainingsLab
import com.sport.workoutapp.data.model.Day
import com.sport.workoutapp.data.model.Training
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DaysViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalendarUiState())
    val uiState: StateFlow<CalendarUiState> = _uiState.asStateFlow()

    init {

    }

    fun setTrainings(newTrainings: List<Training>) {
        _uiState.value = _uiState.value.copy(trainings = newTrainings)
    }
}

data class CalendarUiState(
    var trainings: List<Training> = emptyList()
)