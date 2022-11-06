package com.akki.filmyapp.navigation

sealed class Screen(val screenName: String) {
    object HomeScreen : Screen(screenName = "hone_screen")
    object DetailScreen : Screen(screenName = "detail_screen")
}
