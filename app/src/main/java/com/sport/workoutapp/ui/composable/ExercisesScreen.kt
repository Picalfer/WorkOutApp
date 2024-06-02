package com.sport.workoutapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sport.workoutapp.data.model.Exercise

@Preview
@Composable
fun ExercisesScreen() {
    val exercises = listOf(
        Exercise("Разминка 5-10 мин"),
        Exercise("Выпады с гантелями"),
        Exercise("Сгибания рук с гантелями сидя/стоя"),
        Exercise("Становая тяга с гантелями"),
        Exercise("Сгибания рук с гантелями «молот»"),
        Exercise("Подъём таза лёжа одной ногой"),
        Exercise("Сгибание руки сидя через колено"),
        Exercise("Подъём на носки стоя"),
        Exercise("Заминка 2-5 мин"),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        items(exercises.size) { value ->
            Box(
                modifier = Modifier
                    .border(2.dp, Color.Black, RectangleShape)
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.Gray)
                    .padding(all = 6.dp)
            ) {
                Text(text = exercises[value].title)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}