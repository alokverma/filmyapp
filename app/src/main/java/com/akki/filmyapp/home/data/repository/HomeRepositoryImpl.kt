package com.akki.filmyapp.home.data.repository

import com.akki.filmyapp.api.ApiInterface
import com.akki.filmyapp.home.domain.repository.IHomeRepository
import com.akki.filmyapp.logging.ILogger
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiInterface,
    private val logger: ILogger
) : IHomeRepository {

    override suspend fun getTrendingMovies() {
        apiService.getTrendingMovies()
    }
}