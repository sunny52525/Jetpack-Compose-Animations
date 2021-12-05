package com.example.revealanimation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.revealanimation.screens.Home
import com.example.revealanimation.screens.Reveal

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
    })
}

