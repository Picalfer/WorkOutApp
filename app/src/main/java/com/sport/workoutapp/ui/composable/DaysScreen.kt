package com.sport.workoutapp.ui.composable

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sport.workoutapp.data.model.Day

@Composable
fun DaysScreen(
    onDayClick: (List<String>) -> Unit
) {
    val days = listOf(
        Day(
            "1 день - плечи, трицепс, пресс",
            listOf(
                "Разминка 5-10 мин",
                "Жим гантелей сидя",
                "Отжимания от лавки сзади",
                "Протяжка с гантелями",
                "Французский жим с гантелей",
                "Махи гантелями в стороны",
                "Французский жим с гантелями лёжа",
                "Скручивания лёжа на полу",
                "Заминка 2-5 мин",
            )
        ),
        Day(
            "2 день - ноги и бицепс",
            listOf(
                "Разминка 5-10 мин",
                "Выпады с гантелями",
                "Сгибания рук с гантелями сидя/стоя",
                "Становая тяга с гантелями",
                "Сгибания рук с гантелями «молот»",
                "Подъём таза лёжа одной ногой",
                "Сгибание руки сидя через колено",
                "Подъём на носки стоя",
                "Заминка 2-5 мин",
            )
        ),
        Day(
            "3 день - грудь, спина, пресс",
            listOf(
                "Разминка 5-10 мин",
                "Жим гантелей лёжа",
                "Тяга одной гантели в наклоне",
                "Отжимания от пола широким хватом",
                "Тяга гантелей в наклоне",
                "Разводы с гантелями лёжа",
                "Пуловер с гантелей лёжа",
                "Подъём ног сидя на лавке",
                "Заминка 2-5 мин",
            )
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        repeat(days.size) { value ->
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(16.dp),
                colors = ButtonColors(Color.LightGray, Color.Black, Color.Red, Color.Blue),
                onClick = {
                    onDayClick(days[value].exercises)
                }) {
                Text(text = days[value].title)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}