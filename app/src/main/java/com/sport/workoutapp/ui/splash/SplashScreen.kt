package com.sport.workoutapp.ui.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.sport.workoutapp.R

@Composable
fun SplashScreen(
    onFinished: () -> Unit
) {
    val scale = remember {
        Animatable(0f)
    }

    val rotate = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 300,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )

        rotate.animateTo(
            targetValue = 180f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = {
                    OvershootInterpolator(1f).getInterpolation(it)
                }
            )
        )
        onFinished()
    }

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.splash_icon
            ),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value * 5)
                .rotate(rotate.value)
        )
    }
}