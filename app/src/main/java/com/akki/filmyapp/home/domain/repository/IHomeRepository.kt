package com.akki.filmyapp.home.domain.repository

import com.akki.filmyapp.basemodel.Resource
import com.akki.filmyapp.home.domain.model.MovieList
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {

    suspend fun getTrendingMovies() : Flow<Resource<MovieList>>
}