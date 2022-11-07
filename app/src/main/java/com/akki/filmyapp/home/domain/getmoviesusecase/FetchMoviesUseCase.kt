package com.akki.filmyapp.home.domain.getmoviesusecase

import com.akki.filmyapp.basemodel.Resource
import com.akki.filmyapp.home.domain.model.MovieList
import com.akki.filmyapp.home.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(
    private val repository: IHomeRepository
) {
    operator fun invoke(type: String): kotlinx.coroutines.flow.Flow<Resource<MovieList>> =
        flow {
            try {
                emit(Resource.Loading())
                emit(Resource.Success(repository.getMovies(type)))
            } catch (e: HttpException) {
                emit(Resource.Error("Something went wrong"))
            } catch (e: IOException) {
                emit(Resource.Error("Something went wrong"))
            }
        }
}
