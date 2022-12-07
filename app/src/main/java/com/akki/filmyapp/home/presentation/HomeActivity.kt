package com.akki.filmyapp.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.akki.filmyapp.navigation.SetupNavigationGraph
import com.akki.filmyapp.ui.theme.FilmyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: HomeViewModel by viewModels()
        setContent {
            FilmyAppTheme {
                navHostController = rememberNavController()
                SetupNavigationGraph(
                    navHostController = navHostController,
                    viewModel
                )
            }
        }
    }
}
