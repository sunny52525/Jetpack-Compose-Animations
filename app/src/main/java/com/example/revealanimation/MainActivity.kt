package com.example.revealanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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

    var enabled by remember { mutableStateOf(false) }

    val degree by animateFloatAsState(
        if (enabled) 300f else 0f,
        tween(durationMillis = 500, easing = LinearEasing)
    )
    val card1 by animateFloatAsState(if (enabled) 100f else 0f, tween(durationMillis = 500))
    val card2 by animateFloatAsState(if (enabled) 200f else 0f, tween(durationMillis = 500))
    val card3 by animateFloatAsState(if (enabled) 300f else 00f, tween(durationMillis = 500))
    val card4 by animateFloatAsState(if (enabled) 400f else 00f, tween(durationMillis = 500))
    val card5 by animateFloatAsState(if (enabled) 500f else 00f, tween(durationMillis = 500))



    Box(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        contentAlignment = Alignment.TopEnd
    ) {


        RoundCard(
            modifier = Modifier.offset(y = card5.dp),
        )
        RoundCard(
            modifier = Modifier.offset(y = card4.dp),
        )
        RoundCard(
            modifier = Modifier.offset(y = card3.dp),
        )
        RoundCard(
            modifier = Modifier.offset(y = card2.dp),
        )
        RoundCard(
            modifier = Modifier.offset(y = card1.dp),
        )

        Card(
            onClick = {
                enabled = !enabled
            },
            shape = CircleShape,
            modifier = Modifier.size(100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_settings_gear),
                contentDescription = null,
                Modifier
                    .padding(18.dp)
                    .rotate(degree)
            )
        }

    }
}


@Preview
@Composable
fun RoundCard(modifier: Modifier = Modifier) {
    Card(
        shape = CircleShape,
        modifier = modifier.size(100.dp),
        backgroundColor = Color.Black,

        ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            tint = getRandomColor(),
            modifier = Modifier
                .size(20.dp)
                .padding(18.dp)
        )
    }
}


private fun getRandomColor(): Color {
    val random = Random
    val red = random.nextInt(256)
    val green = random.nextInt(256)
    val blue = random.nextInt(256)
    return Color(red, green, blue)
}

