package com.akki.filmyapp.home.domain.model

data class HomeState(val tabs: List<MovieTabs>, val movieList: MovieList?)

data class MovieTabs(val type: String, val title:String)
