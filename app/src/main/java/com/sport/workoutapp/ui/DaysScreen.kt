package com.sport.workoutapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DaysScreen(
    daysViewModel: DaysViewModel = viewModel(),
    onDayClick: (Int) -> Unit
) {
    val daysUiState by daysViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        repeat(daysUiState.days.size) { value ->
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonColors(
                    daysUiState.days[value].color,
                    Color.Black,
                    Color.Red,
                    Color.Blue
                ),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    onDayClick(value)
                }) {
                Text(
                    text = daysUiState.days[value].title,
                    modifier = Modifier
                        .padding(12.dp),
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}