package com.example.jetpackcomposesample.presentation.animation

import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposesample.presentation.animation.PulseAnimationDefinitions.pulseDefinition
import com.example.jetpackcomposesample.presentation.animation.PulseAnimationDefinitions.pulsePropKey

@Composable
fun PulsingDemo() {
    val color = MaterialTheme.colors.primary
    val pulseAnim = transition(
        definition = pulseDefinition,
        toState = PulseAnimationDefinitions.PulseState.FINAL,
        initState = PulseAnimationDefinitions.PulseState.INITIAL
    )
    val pulseMagnitude = pulseAnim[pulsePropKey]

    Row(modifier = Modifier.fillMaxWidth().height(100.dp),
    horizontalArrangement = Arrangement.Center) {
        Image(
            imageVector = Icons.Default.Favorite,
            modifier = Modifier
                .height(pulseMagnitude.dp)
                .width(pulseMagnitude.dp)
                .align(Alignment.CenterVertically)
        )
    }

    Canvas(modifier = Modifier.fillMaxWidth().height(55.dp)) {
        drawCircle(
            radius = pulseMagnitude,
            brush = SolidColor(color)
        )
    }
}

object PulseAnimationDefinitions {

    enum class PulseState {
        INITIAL,
        FINAL
    }

    val pulsePropKey = FloatPropKey("pulseKey")

    val pulseDefinition = transitionDefinition<PulseState> {
        state(PulseState.INITIAL) {
            this[pulsePropKey] = 20f
        }
        state(PulseState.FINAL) {
            this[pulsePropKey] = 40f
        }

        transition(
            PulseState.INITIAL to PulseState.FINAL
        ) {
            pulsePropKey using infiniteRepeatable(
                animation = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        }
    }
}