package com.sport.workoutapp.ui.calendar

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sport.workoutapp.data.TrainingsLab
import com.sport.workoutapp.data.model.Day
import com.sport.workoutapp.data.model.Training
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import org.mongodb.kbson.ObjectId
import java.time.LocalDate
import java.time.YearMonth
import java.util.Calendar
import java.util.Locale

@Composable
fun CalendarScreen(
    calendarViewModel: DaysViewModel = viewModel(),
) {
    val calendarUiState by calendarViewModel.uiState.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(calendarUiState) {
        val newTrainings = TrainingsLab.getInstance(context).getTrainings()
        calendarViewModel.setTrainings(newTrainings)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {

            //MyCalendarScreen()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {

                items(calendarUiState.trainings.toList()) { training ->
                    Row(
                        modifier = Modifier
                            .background(Color.Gray)
                            .padding(7.dp)
                            .fillMaxWidth()
                    ) {
                        Column {
                            Text(training.title)
                            Text(getFullDateOfTraining(training))
                        }
                        Button(onClick = {
                            TrainingsLab.getInstance(context).deleteTraining(training)
                        }) {
                            Text("Удалить")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        }

    }
}

@Composable
fun MyCalendarScreen() {
    val currentYearMonth = YearMonth.now()
    val daysInMonth = currentYearMonth.lengthOfMonth()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "${currentYearMonth.month} ${currentYearMonth.year}",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            (1..7).map { day ->
                Text(
                    text = when (day) {
                        1 -> "Sun"
                        2 -> "Mon"
                        3 -> "Tue"
                        4 -> "Wed"
                        5 -> "Thu"
                        6 -> "Fri"
                        7 -> "Sat"
                        else -> ""
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        val firstDayOfMonth =
            LocalDate.of(currentYearMonth.year, currentYearMonth.monthValue, 1).dayOfWeek.value
        val daysBeforeFirst = (firstDayOfMonth - 1) % 7

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalArrangement = Arrangement.Center
        ) {
            items(daysBeforeFirst) { Spacer(modifier = Modifier.width(48.dp)) }

            items(daysInMonth + daysBeforeFirst) { dayIndex ->
                DayCell(
                    day = if (dayIndex >= daysBeforeFirst) dayIndex - daysBeforeFirst + 1 else dayIndex,
                    isCompleted = dayIndex in 9..10 || dayIndex in 28..30
                )
            }
        }
    }
}

@Composable
fun DayCell(day: Int, isCompleted: Boolean) {
    Box(
        modifier = Modifier
            .width(48.dp)
            .height(48.dp)
            .padding(4.dp)
            .background(if (isCompleted) Color.LightGray else Color.White),
        contentAlignment = Alignment.Center
    ) {
        if (day != 0) {
            Text(
                text = day.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )

            if (isCompleted) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red, shape = RoundedCornerShape(4.dp))
                        .alpha(0.3f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Done",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                }
            }
        }
    }
}

fun getStringDateOfTraining(training: Training): String {
    val year = training.date.get(Calendar.YEAR).toString()
    val month = training.date.get(Calendar.MONTH).toString()
    val day = training.date.get(Calendar.DAY_OF_MONTH).toString()

    return "$year/$month/$day"
}

fun getFullDateOfTraining(training: Training): String {
    val date = getStringDateOfTraining(training)
    val time = getStringTimeOfTraining(training)

    return "$date ($time)"
}

fun getStringTimeOfTraining(training: Training): String {
    val hour =
        training.date.get(Calendar.HOUR_OF_DAY).toString()
    var minute =
        training.date.get(Calendar.MINUTE).toString()

    if (minute.toInt() < 10) minute = String.format(Locale.getDefault(), "0%s", minute)

    return "$hour:$minute"
}