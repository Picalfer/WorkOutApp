package com.sport.workoutapp.ui.newday.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sport.workoutapp.ui.exercises.ExerciseItem
import com.sport.workoutapp.ui.theme.BtnTimerColor

@Composable
fun NewDayExercisesScreen(
    newDayExercisesViewModel: NewDayExercisesViewModel = viewModel(),
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    val newDayExercisesUiState by newDayExercisesViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.height(10.dp))
        Text(
            text = "Выберите упражнения",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 17.sp,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            items(newDayExercisesUiState.exercises) { currentExercise ->
                ExerciseItem(currentExercise = currentExercise, onCheckboxChange = {
                    newDayExercisesViewModel.changeExerciseDoneStatus(currentExercise._id, it)
                })
            }
        }

        Spacer(modifier = Modifier.height(7.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GreenButton(onClick = { onBackClick() }, "Выйти")
            GreenButton(onClick = { onNextClick() }, "Далее")
        }

        Spacer(modifier = Modifier.height(9.dp))
    }
}

@Composable
fun GreenButton(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 24.dp, vertical = 14.dp),
        colors = ButtonColors(BtnTimerColor, Color.White, Color.Black, Color.White),

        ) {
        Text(text = text, modifier = Modifier.padding(0.dp))
    }
}