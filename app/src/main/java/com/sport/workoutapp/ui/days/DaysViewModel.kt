package com.sport.workoutapp.ui.days

import androidx.lifecycle.ViewModel
import com.sport.workoutapp.data.getDaysData
import com.sport.workoutapp.data.model.Day
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DaysViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DaysUiState())
    val uiState: StateFlow<DaysUiState> = _uiState.asStateFlow()

    init {
        updateDays()
    }

    private fun updateDays() {
        _uiState.value = DaysUiState(days = getDaysData())
    }
}

data class DaysUiState(
    val days: List<Day> = emptyList()
)