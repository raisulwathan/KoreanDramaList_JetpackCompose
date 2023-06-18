package com.example.submission.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailSerial : Screen("home/{serialId}") {
        fun createRoute(serialId: Long) = "home/$serialId"
    }
}
