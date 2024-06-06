package com.sport.workoutapp.ui

import androidx.lifecycle.ViewModel
import com.sport.workoutapp.data.getDaysData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DaysViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DaysUiState())
    val uiState: StateFlow<DaysUiState> = _uiState.asStateFlow()

    init {
        updateDays()
    }

    fun updateDays() {
        _uiState.value = DaysUiState(days = getDaysData())
    }
}