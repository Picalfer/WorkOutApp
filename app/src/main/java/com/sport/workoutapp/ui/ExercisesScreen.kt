package com.sport.workoutapp.ui

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

        ExercisesHeader(exercisesUiState.dayTitle)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            items(exercises) { currentExercise ->
                ExerciseItem(currentExercise = currentExercise) {
                    exercisesViewModel.changeExerciseIsDone(currentExercise.title)
                }
            }
        }
    }
}


@Composable
fun ExercisesHeader(dayTitle: String) {
    Text(
        text = "День: ${dayTitle}", fontSize = 20.sp, modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = Color.Black
    )

    Spacer(modifier = Modifier.height(12.dp))

    Text(
        text = "Упражнения", fontSize = 18.sp, modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = Color.Black
    )

    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun ExerciseItem(
    currentExercise: Exercise,
    onCheckboxChange: () -> Unit
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
                    onCheckboxChange()
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