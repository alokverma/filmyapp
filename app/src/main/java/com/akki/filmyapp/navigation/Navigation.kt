package com.akki.filmyapp.navigation // ktlint-disable filename

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akki.filmyapp.home.presentation.HomeScreenV2
import com.akki.filmyapp.home.presentation.details.DetailScreen

@Composable
fun SetupNavigationGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.screenName
    ) {
        composable(
            route = Screen.HomeScreen.screenName
        ) {
            HomeScreenV2(navController = navHostController)
        }
        composable(
            route = Screen.DetailScreen.screenName
        ) {
            DetailScreen(navHostController = navHostController)
        }
    }
}
