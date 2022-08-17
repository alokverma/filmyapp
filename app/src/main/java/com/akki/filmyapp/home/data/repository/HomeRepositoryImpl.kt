package com.akki.filmyapp.home.data.repository

import com.akki.filmyapp.api.ApiInterface
import com.akki.filmyapp.basemodel.Resource
import com.akki.filmyapp.home.domain.model.MovieList
import com.akki.filmyapp.home.domain.repository.IHomeRepository
import com.akki.filmyapp.logging.ILogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiInterface,
    private val logger: ILogger
) : IHomeRepository {

    override suspend fun getTrendingMovies() : Flow<Resource<MovieList>> {
        return flow {
            try {
                emit(Resource.Loading(true))
                emit(Resource.Success(data = apiService.getTrendingMovies()))
            } catch ( io : Exception){
                logger.logException(io)
                emit(Resource.Loading(false))
                emit(Resource.Error("Something went wrong"))
            } catch (e: Exception) {
                logger.logException(e)
                emit(Resource.Loading(false))
                emit(Resource.Error("Something went wrong, please try again"))
            }
        }

    }
}