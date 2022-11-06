package com.akki.filmyapp.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.akki.filmyapp.ui.theme.FilmyAppTheme
import com.akki.filmyapp.ui.theme.Typography
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmyAppTheme {
                HomeScreenV2 {
                }
            }
        }
    }

    @Composable
    fun Greetings(name: String) {
        Text(text = name)
    }

    @Composable
    fun CustomText(name: String) {
        Text(
            text = name,
            style = Typography.body1
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        CustomText(name = "Hi Filmy")
    }
}
