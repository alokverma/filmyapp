package com.akki.filmyapp.home.domain.repository

import com.akki.filmyapp.basemodel.Resource
import com.akki.filmyapp.home.domain.model.Genre
import com.akki.filmyapp.home.domain.model.MovieList
import com.akki.filmyapp.home.domain.model.MovieTabs
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {

    suspend fun getMovies(type: String) : Flow<Resource<MovieList>>

    suspend fun fetchGenre(): Flow<Resource<Genre>>

    suspend fun fetchTabs(): Flow<List<MovieTabs>>
}