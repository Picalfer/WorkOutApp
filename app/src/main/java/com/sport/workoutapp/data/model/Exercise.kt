package com.sport.workoutapp.data.model

data class Exercise(
    val title: String,
    var isDone: Boolean = false,
    val type: ExerciseType = ExerciseType.Standard
)

sealed class ExerciseType {
    object WarmUp : ExerciseType()
    object Standard : ExerciseType()
    object WarmDown : ExerciseType()
}