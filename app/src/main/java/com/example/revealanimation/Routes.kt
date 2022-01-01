package com.example.revealanimation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Reveal : Routes("reveal")
    object SlideZoom : Routes("SlideZoom")
    object RatingBar : Routes("RatingBar")
    object TelegramVoice : Routes("TelegramVoice")
}
