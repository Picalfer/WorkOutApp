package com.sport.workoutapp.ui.days

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sport.workoutapp.data.model.Day
import org.mongodb.kbson.ObjectId

@Composable
fun DaysScreen(
    daysViewModel: DaysViewModel = viewModel(),
    onDayClick: (ObjectId) -> Unit,
    onBtnAddDayClick: () -> Unit,
) {
    val daysUiState by daysViewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            items(daysUiState.days) { day ->
                DayItem(
                    day = day,
                    onClick = onDayClick
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        ImageButton(
            modifier = Modifier
                .align(Alignment.TopStart),
            text = "Добавить день",
            onClick = onBtnAddDayClick
        )

        ImageButton(
            modifier = Modifier
                .align(Alignment.TopEnd),
            text = "Добавить упражнение",
            onClick = {}
        )

        ImageButton(
            modifier = Modifier
                .align(BottomStart),
            text = "Все упражнения",
            onClick = {}
        )

        ImageButton(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            text = "Удалить день",
            onClick = {}
        )
    }
}

@Composable
fun DayItem(day: Day, onClick: (ObjectId) -> Unit) {
    Button(
        modifier = Modifier.fillMaxSize(),
        colors = ButtonColors(
            day.getColor(),
            Color.Black,
            Color.Red,
            Color.Blue
        ),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onClick(day._id)
        }) {
        Text(
            text = day.title,
            modifier = Modifier
                .padding(12.dp),
            fontSize = 16.sp
        )
    }
}

@Composable
fun ImageButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}