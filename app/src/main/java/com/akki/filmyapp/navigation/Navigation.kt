package com.akki.filmyapp.navigation // ktlint-disable filename

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.akki.filmyapp.home.presentation.HomeScreenV2
import com.akki.filmyapp.home.presentation.details.DetailScreen
import timber.log.Timber

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
            route = Screen.DetailScreen.screenName,
            arguments = listOf(
                navArgument(DETAIL_SCREEN_KEY) {
                    type = NavType.IntType
                },
                navArgument(DETAIL_SCREEN_KEY2) {
                    type = NavType.StringType
                },
                navArgument(DETAIL_SCREEN_KEY3) {
                    type = NavType.StringType
                },
                navArgument(DETAIL_SCREEN_KEY4) {
                    type = NavType.StringType
                }
            )
        ) {
            Timber.tag("alok").d(it.arguments?.getInt(DETAIL_SCREEN_KEY).toString())
            DetailScreen(
                navHostController = navHostController,
                movieId = it.arguments?.getInt(DETAIL_SCREEN_KEY) ?: -1,
                movieName = it.arguments?.getString(DETAIL_SCREEN_KEY2).orEmpty(),
                description = it.arguments?.getString(DETAIL_SCREEN_KEY4).orEmpty()
            )
        }
    }
}
