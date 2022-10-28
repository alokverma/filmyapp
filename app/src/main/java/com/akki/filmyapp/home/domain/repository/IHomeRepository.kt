package com.akki.filmyapp.home.domain.repository

import com.akki.filmyapp.basemodel.Resource
import com.akki.filmyapp.home.domain.model.Genre
import com.akki.filmyapp.home.domain.model.MovieList
import com.akki.filmyapp.home.domain.model.MovieTabs
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {

    suspend fun getMovies(type: String) : MovieList

    suspend fun fetchGenre(): Genre?

    suspend fun fetchTabs(): MovieTabs?
}