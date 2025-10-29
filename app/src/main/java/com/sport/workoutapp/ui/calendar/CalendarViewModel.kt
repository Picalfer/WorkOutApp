package com.sport.workoutapp.ui.calendar

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sport.workoutapp.data.TrainingsLab
import com.sport.workoutapp.data.model.Training
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CalendarViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalendarUiState())
    val uiState: StateFlow<CalendarUiState> = _uiState.asStateFlow()

    init {

    }

    fun setTrainings(newTrainings: List<Training>) {
        _uiState.value = _uiState.value.copy(trainings = newTrainings)
    }

    // TODO dont transfer context to viewModel!
    fun onImportClicked(uri: Uri, trainingsLab: TrainingsLab, context: Context) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val importedCount = trainingsLab.importTrainingsFromUri(uri, context)
            // Обновляем UI после импорта
            setTrainings(trainingsLab.getTrainings())
            _uiState.update {
                it.copy(
                    isLoading = false,
                    message = "Импортировано $importedCount тренировок"
                )
            }
        }
    }

    // TODO dont transfer context to viewModel!
    fun onExportClicked(trainingsLab: TrainingsLab, context: Context) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val success = trainingsLab.exportTrainings(context)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    message = if (success) "Экспорт завершен" else "Ошибка экспорта"
                )
            }
        }
    }

    fun clearMessage() {
        _uiState.update { it.copy(message = null) }
    }
}

data class CalendarUiState(
    val trainings: List<Training> = emptyList(),
    val isLoading: Boolean = false,
    val message: String? = null,
)