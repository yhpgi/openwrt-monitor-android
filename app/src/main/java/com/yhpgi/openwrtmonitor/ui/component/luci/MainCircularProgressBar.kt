package com.yhpgi.openwrtmonitor.ui.component.luci

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainCircularProgressBar(
    percentage: Float,
    title: String,
    number: Int = 100,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = 8.dp,
    animationDuration: Int = 1000,
    animationDelay: Int = 0,
    textModifier: String
) {
    var animated by remember {
        mutableStateOf(false)
    }
    val currentPercentage = animateFloatAsState(
        targetValue = if (animated) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        ),
        label = ""
    )
    LaunchedEffect(key1 = true) {
        animated = true
    }

    Card {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(radius * 3f)
            ) {
                Canvas(modifier = Modifier.size(radius * 2f)) {
                    drawArc(
                        color = color,
                        startAngle = 130f,
                        sweepAngle = 280 * currentPercentage.value,
                        useCenter = false,
                        style = Stroke(
                            width = strokeWidth.toPx(),
                            cap = StrokeCap.Round
                        )
                    )
                }
                Text(
                    text = "${(currentPercentage.value * number).toInt()}$textModifier",
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}

