package com.sport.workoutapp.ui.composable

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sport.workoutapp.data.model.Day
import com.sport.workoutapp.ui.theme.Day1Color
import com.sport.workoutapp.ui.theme.Day2Color
import com.sport.workoutapp.ui.theme.Day3Color

@Composable
fun DaysScreen(
    onDayClick: (List<String>) -> Unit
) {
    val days = listOf(
        Day(
            "Плечи + трицепс + пресс",
            Day1Color,
            listOf(
                "Разминка 5-10 мин",
                "1. Жим гантелей сидя",
                "2. Отжимания от лавки сзади",
                "3. Протяжка с гантелями",
                "4. Французский жим с гантелей",
                "5. Махи гантелями в стороны",
                "6. Французский жим с гантелями лёжа",
                "7. Скручивания лёжа на полу",
                "Заминка 2-5 мин",
            )
        ),
        Day(
            "Ноги + бицепс",
            Day2Color,
            listOf(
                "Разминка 5-10 мин",
                "1. Выпады с гантелями",
                "2. Сгибания рук с гантелями сидя/стоя",
                "3. Становая тяга с гантелями",
                "4. Сгибания рук с гантелями «молот»",
                "5. Подъём таза лёжа одной ногой",
                "6. Сгибание руки сидя через колено",
                "7. Подъём на носки стоя",
                "Заминка 2-5 мин",
            )
        ),
        Day(
            "Грудь + спина + пресс",
            Day3Color,
            listOf(
                "Разминка 5-10 мин",
                "1. Жим гантелей лёжа",
                "2. Тяга одной гантели в наклоне",
                "3. Отжимания от пола широким хватом",
                "4. Тяга гантелей в наклоне",
                "5. Разводы с гантелями лёжа",
                "6. Пуловер с гантелей лёжа",
                "7. Подъём ног сидя на лавке",
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
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonColors(days[value].color, Color.Black, Color.Red, Color.Blue),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    onDayClick(days[value].exercises)
                }) {
                Text(
                    text = days[value].title,
                    modifier = Modifier
                        .padding(12.dp),
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}