package com.example.revealanimation.screens

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.revealanimation.R.*
import kotlin.random.Random


@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview(
    showBackground = true, backgroundColor = 0xFFF5F1F1,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
fun Reveal() {

    var expanded by remember { mutableStateOf(false) }
    val numberOfCard by remember { mutableStateOf(8) }
    val degree by animateFloatAsState(
        targetValue = if (expanded) 300f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
    )
    val position by animateFloatAsState(
        targetValue = if (expanded) 50f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        (numberOfCard downTo 1).forEach { i ->
            RoundCard(
                modifier = Modifier.offset(y = (i * position).dp),
            )
        }
        Card(
            onClick = {
                expanded = expanded.not()
            },
            shape = CircleShape,
            modifier = Modifier.size(60.dp)
        ) {
            Image(
                painter = painterResource(id = drawable.ic_settings_gear),
                contentDescription = null,
                Modifier
                    .padding(8.dp)
                    .rotate(degree)
            )
        }

    }
}


@Preview
@Composable
fun RoundCard(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .height(60.dp)
            .width(50.dp)
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            backgroundColor = Color.Black,

            ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = getRandomColor(),
                modifier = Modifier
                    .size(20.dp)
                    .padding(8.dp)
            )
        }
    }

}


private fun getRandomColor(): Color {
    val random = Random
    val red = random.nextInt(256)
    val green = random.nextInt(256)
    val blue = random.nextInt(256)
    return Color(red, green, blue)
}
