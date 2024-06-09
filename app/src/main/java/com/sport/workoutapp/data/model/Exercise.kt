package com.sport.workoutapp.data.model

import androidx.annotation.RawRes

data class Exercise(
    val title: String,
    var isDone: Boolean = false,
    val type: ExerciseType = ExerciseType.Standard,
    val sets: String = "",
    val reps: String = "",
    @RawRes val gifResId: Int? = null
)

sealed class ExerciseType {
    data object WarmUp : ExerciseType()
    data object Standard : ExerciseType()
    data object WarmDown : ExerciseType()
}