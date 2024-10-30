package com.sport.workoutapp.ui.days

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sport.workoutapp.WorkOutApplication.Companion.realm
import com.sport.workoutapp.data.model.Day
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DaysViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DaysUiState())
    val uiState: StateFlow<DaysUiState> = _uiState.asStateFlow()

    private val allDaysFlow = realm
        .query<Day>()
        .asFlow()
        .map { results ->
            results.list.toList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    init {
        observeDays()
    }

    private fun observeDays() {
        viewModelScope.launch {
            allDaysFlow.collect { days ->
                _uiState.value = _uiState.value.copy(days = days)
            }
        }
    }
}

data class DaysUiState(
    val days: List<Day> = emptyList()
)