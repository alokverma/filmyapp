package com.akki.filmyapp.home.domain.repository

interface IHomeRepository {

    suspend fun getTrendingMovies()
}