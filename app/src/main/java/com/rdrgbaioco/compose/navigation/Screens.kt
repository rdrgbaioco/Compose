package com.rdrgbaioco.compose.navigation


sealed class Screens(val route: String) {
    object Home : Screens("home_screen")
    object User : Screens("user_screen")
    object UserDetails : Screens("user_details_screen")
}