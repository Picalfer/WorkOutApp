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
import com.sport.workoutapp.data.days

@Composable
fun DaysScreen(
    onDayClick: (Int) -> Unit
) {

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
                    onDayClick(value)
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