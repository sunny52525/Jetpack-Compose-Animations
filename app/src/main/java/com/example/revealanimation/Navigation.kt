package com.example.revealanimation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.revealanimation.screens.*

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route, builder = {
        composable(Routes.Home.route) {
            Home {
                navController.navigate(it.route)
            }
        }

        composable(Routes.Reveal.route) {
            Reveal()
        }
        composable(Routes.SlideZoom.route) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentAlignment = Alignment.BottomStart
            ) {
                TelegramStickerScroll()
            }
        }
        composable(Routes.RatingBar.route){
            RatingBar()
        }
        composable(Routes.TelegramVoice.route){
            TelegramVoiceRecord()
        }
    })
}

