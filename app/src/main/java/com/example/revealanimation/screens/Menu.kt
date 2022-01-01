package com.example.revealanimation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.revealanimation.Routes


@Composable
fun Home(onClick: (Routes) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Button(onClick = { onClick(Routes.Reveal) }) {
                Text(text = "Reveal")
            }
            Button(onClick = { onClick(Routes.SlideZoom) }) {
                Text(text = "SlideZoom")
            }
            Button(onClick = { onClick(Routes.SlideZoom) }) {
                Text(text = Routes.RatingBar.route)
            }
            Button(onClick = {
                onClick(Routes.TelegramVoice)
            }) {
                Text(text = Routes.TelegramVoice.route)
            }

        }
    }

}
