package com.example.composeapp.appUtils

sealed class BottomNavRoutes(var route: String) {
    object Home : BottomNavRoutes("home_screen")
    object Explore : BottomNavRoutes("explore_screen")
    object Upload : BottomNavRoutes("upload_screen")
    object Inbox : BottomNavRoutes("inbox_screen")
    object Profile : BottomNavRoutes("profile_screen")
}
