package com.sport.workoutapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sport.workoutapp.data.model.Day
import com.sport.workoutapp.data.model.Exercise

@Preview
@Composable
fun MainScreen() {
    val days = listOf(
        Day("День 1", listOf(
            Exercise("Разминка 5-10 мин"),
            Exercise("Выпады с гантелями"),
            Exercise("Сгибания рук с гантелями сидя/стоя"),
            Exercise("Становая тяга с гантелями"),
            Exercise("Сгибания рук с гантелями «молот»"),
            Exercise("Подъём таза лёжа одной ногой"),
            Exercise("Сгибание руки сидя через колено"),
            Exercise("Подъём на носки стоя"),
            Exercise("Заминка 2-5 мин"),
        ))
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center) {
        repeat(days.size) { value ->
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(16.dp),
                colors = ButtonColors(Color.LightGray, Color.Black, Color.Red, Color.Blue),
                onClick = {
                    //ExercisesScreen(exercises = days[value].exercises)
                }) {
                Text(text = days[value].title)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}