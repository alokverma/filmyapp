package com.akki.filmyapp.home.presentation

import androidx.compose.ui.graphics.Color

data class MovieListItem(
    val imageUrl: String = "",
    val name: String = "",
    val description: String = "",
    val rating: Double = 0.0,
    val background: Color = Color.Magenta
)
