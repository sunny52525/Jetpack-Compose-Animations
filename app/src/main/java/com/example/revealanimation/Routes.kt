package com.example.revealanimation

// It contains route names to all three screens
sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Reveal : Routes("reveal")
    object Route2 : Routes("route1")
}
