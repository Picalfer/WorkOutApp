package com.sport.workoutapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sport.workoutapp.ui.theme.ExerciseColor
import com.sport.workoutapp.ui.theme.WarmDownColor
import com.sport.workoutapp.ui.theme.WarmUpColor

@Composable
fun ExercisesScreen(exercises: List<String>) {

    Column(
        modifier = Modifier
            .padding(24.dp)
    ) {
        Text(
            text = "Упражнения", fontSize = 20.sp, modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            items(exercises.size) { value ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (value == 0) WarmUpColor else if (value == exercises.size - 1) WarmDownColor else ExerciseColor)
                        .padding(all = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = exercises[value], modifier = Modifier
                                .padding(10.dp)
                                .weight(1f)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        var isChecked by remember { mutableStateOf(false) }
                        Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}