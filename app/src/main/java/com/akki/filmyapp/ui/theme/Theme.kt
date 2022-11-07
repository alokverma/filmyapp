package com.akki.filmyapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

val DarkColorTheme = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200

)

val LightColorTheme = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
)

@Composable
fun FilmyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorTheme
    } else {
        LightColorTheme
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}
