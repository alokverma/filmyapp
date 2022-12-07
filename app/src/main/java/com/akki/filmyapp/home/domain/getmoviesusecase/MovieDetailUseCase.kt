package com.akki.filmyapp.home.domain.getmoviesusecase

import com.akki.filmyapp.basemodel.Resource
import com.akki.filmyapp.home.domain.repository.IHomeRepository
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: IHomeRepository
) {

    operator fun invoke(): Flow<Resource<JsonObject>> {
        return flow {
            try {
                emit(Resource.Loading(true))
                emit(Resource.Success(repository.movieDetails()))
            } catch (e: HttpException) {
                emit(Resource.Error("Something went wrong"))
            }
        }
    }
}
