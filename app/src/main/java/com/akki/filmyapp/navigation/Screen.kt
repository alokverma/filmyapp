package com.akki.filmyapp.navigation

const val DETAIL_SCREEN_KEY = "movieId"
const val DETAIL_SCREEN_KEY2 = "movieName"
const val DETAIL_SCREEN_KEY3 = "movieImage"
const val DETAIL_SCREEN_KEY4 = "description"

sealed class Screen(val screenName: String) {
    object HomeScreen : Screen(screenName = "home_screen")
    object DetailScreen :
        Screen(
            screenName = "detail_screen/{$DETAIL_SCREEN_KEY}" +
                "/{$DETAIL_SCREEN_KEY2}/{$DETAIL_SCREEN_KEY3}/{$DETAIL_SCREEN_KEY4}"
        ) {
        fun passMovieNameImageId(
            movieName: String,
            movieImage: String,
            movieId: Int,
            description: String
        ): String {
            return "detail_screen/$movieId/$movieName/$movieImage/$description"
        }
    }
}
