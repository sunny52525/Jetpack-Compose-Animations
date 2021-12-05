package com.example.revealanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
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
import com.example.revealanimation.ui.theme.RevealAnimationTheme
import kotlin.random.Random

@OptIn(ExperimentalMaterialApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RevealAnimationTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Reveal()
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
private fun Reveal() {

    var expanded by remember { mutableStateOf(false) }

    val numberOfCard by remember { mutableStateOf(5) }

    val degree by animateFloatAsState(
        targetValue = if (expanded) 300f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        )
    )
    val position by animateFloatAsState(
        targetValue = if (expanded) 50f else 0f,
        animationSpec = tween(durationMillis = 500)
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        contentAlignment = Alignment.TopEnd
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
                painter = painterResource(id = R.drawable.ic_settings_gear),
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
            .padding(vertical = 10.dp)
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


