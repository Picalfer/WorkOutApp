package com.sport.workoutapp.ui.newday.addons

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sport.workoutapp.ui.exercises.ExerciseItem
import com.sport.workoutapp.ui.newday.exercises.GreenButton

@Composable
fun NewDayAddonsScreen(
    newDayAddonsViewModel: NewDayAddonsViewModel = viewModel(),
    onBackClick: () -> Unit,
) {
    val newDayAddonsUiState by newDayAddonsViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(Modifier.height(10.dp))
        Text(
            text = "Выберите упражнения",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 17.sp,
        )
        Spacer(modifier = Modifier.height(7.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Top
            ) {
                items(newDayAddonsUiState.exercises) { currentExercise ->
                    ExerciseItem(currentExercise = currentExercise, onCheckboxChange = {
                        newDayAddonsViewModel.changeExerciseDoneStatus(currentExercise._id, it)
                    })
                }
            }
        }


        Spacer(modifier = Modifier.height(7.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GreenButton(onClick = { onBackClick() }, "Выйти")
            GreenButton(onClick = { }, "Далее")
        }

        Spacer(modifier = Modifier.height(9.dp))
    }

    // TODO видно различие размеов Spacer и padding при переключении с NewDayExercises
}