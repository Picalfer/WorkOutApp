package com.sport.workoutapp.ui

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.sport.workoutapp.data.model.Exercise
import com.sport.workoutapp.data.model.ExerciseType
import com.sport.workoutapp.ui.theme.BtnTimerColor
import com.sport.workoutapp.ui.theme.ExerciseColor
import com.sport.workoutapp.ui.theme.WarmDownColor
import com.sport.workoutapp.ui.theme.WarmUpColor

@Composable
fun ExercisesScreen(
    exercisesViewModel: ExercisesViewModel = viewModel(),
    dayNumber: Int
) {
    val exercisesUiState by exercisesViewModel.uiState.collectAsState()
    val exercises = exercisesUiState.exercises

    LaunchedEffect(dayNumber) {
        exercisesViewModel.updateDay(dayNumber)
    }

    Column(
        modifier = Modifier
            .padding(24.dp)
    ) {
        LinearProgressIndicator(
            progress = { exercisesUiState.progress },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        ExercisesHeader(exercisesUiState.dayTitle, onBtnTimerClick = {
            exercisesViewModel.startTimer()
        })

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Top
        ) {
            items(exercises) { currentExercise ->
                ExerciseItem(currentExercise = currentExercise, onCheckboxChange = {
                    exercisesViewModel.changeExerciseDoneStatus(currentExercise.title, it)
                })
            }
        }
    }

    if (exercisesUiState.isTimerNow) TimerScreen(
        exercisesUiState.timerSeconds,
        onStop = {
            exercisesViewModel.stopTimer()
        }
    )
}

@Composable
fun TimerScreen(
    timerSeconds: Int,
    onStop: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Color.Gray.copy(alpha = 0.5f))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(0.7f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .background(Color.Black),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = timerSeconds.toString(),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier
                )
            }
        }

        Button(
            onClick = { onStop() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            colors = ButtonColors(BtnTimerColor, Color.White, Color.Black, Color.White),
        ) {
            Text(text = "Stop timer")
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun ExercisesHeader(
    dayTitle: String,
    onBtnTimerClick: () -> Unit
) {
    Text(
        text = "День: ${dayTitle}", fontSize = 20.sp, modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = Color.Black
    )

    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Упражнения:", fontSize = 18.sp, modifier = Modifier
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Button(
            onClick = { onBtnTimerClick() },
            modifier = Modifier
                .background(Color.Transparent)
                .padding(horizontal = 24.dp, vertical = 14.dp)
                .align(Alignment.CenterVertically),
            colors = ButtonColors(BtnTimerColor, Color.White, Color.Black, Color.White),

            ) {
            Text(text = "Start timer", modifier = Modifier.padding(0.dp))
        }
    }

    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun ExerciseItem(
    currentExercise: Exercise,
    onCheckboxChange: (Boolean) -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (currentExercise.type == ExerciseType.WarmUp) WarmUpColor else if (currentExercise.type == ExerciseType.WarmDown) WarmDownColor else ExerciseColor)
            .padding(all = 6.dp)
            .clickable {
                if (currentExercise.type == ExerciseType.Standard) isVisible =
                    !isVisible
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = currentExercise.title, modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Checkbox(
                checked = currentExercise.isDone,
                onCheckedChange = {
                    onCheckboxChange(it)
                }
            )
        }
        if (isVisible && currentExercise.type == ExerciseType.Standard) {
            val context = LocalContext.current
            val imageLoader = ImageLoader.Builder(context)
                .components {
                    if (SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()

            Row {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context).data(data = currentExercise.gifResId)
                            .apply(block = {
                                size(Size.ORIGINAL)
                            }).build(), imageLoader = imageLoader
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(0.5f)
                )
                Column {
                    Text(text = " • ${currentExercise.sets} подходов", fontSize = 16.sp)
                    Text(text = " • ${currentExercise.reps} повторений", fontSize = 16.sp)
                }
            }

        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}