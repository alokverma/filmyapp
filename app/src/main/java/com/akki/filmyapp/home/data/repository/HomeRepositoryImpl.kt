package com.akki.filmyapp.home.data.repository

import com.akki.filmyapp.api.ApiInterface
import com.akki.filmyapp.basemodel.Resource
import com.akki.filmyapp.home.domain.model.Genre
import com.akki.filmyapp.home.domain.model.MovieList
import com.akki.filmyapp.home.domain.model.MovieTabs
import com.akki.filmyapp.home.domain.repository.IHomeRepository
import com.akki.filmyapp.logging.ILogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiInterface,
    private val logger: ILogger
) : IHomeRepository {


    //    override suspend fun getMovies(type: String) =
//         flow {
//            try {
//                emit(Resource.Loading(true))
//                emit(Resource.Success(data = apiService.getMovies(type)))
//            } catch ( io : IOException){
//                logger.logException(io)
//                emit(Resource.Loading(false))
//                emit(Resource.Error("Something went wrong"))
//            } catch (e: HttpException) {
//                logger.logException(e)
//                emit(Resource.Loading(false))
//                emit(Resource.Error("Something went wrong, please try again"))
//            }
//        }
//
//    override suspend fun fetchGenre(): Flow<Resource<Genre>> = flow {
//        try {
//            emit(Resource.Loading(true))
//            emit(Resource.Success(apiService.getGenere()))
//        } catch (io: IOException) {
//            logger.logException(io)
//            emit(Resource.Loading(false))
//            emit(Resource.Error("Something went wrong, please try again"))
//        }
//    }
//
//    override suspend fun fetchTabs(): Flow<List<MovieTabs>> {
//        return flow {
//            val tabs = mutableListOf<MovieTabs>()
//            tabs.add(MovieTabs("now_playing", "Now Playing"))
//            tabs.add(MovieTabs("top_rated", "Top Rated"))
//            tabs.add(MovieTabs("upcoming", "Upcoming"))
//            emit(tabs)
//        }
//    }
    override suspend fun getMovies(type: String): MovieList {
        return apiService.getMovies(type)
    }

    override suspend fun fetchGenre(): Genre? {
        return apiService.getGenere()
    }

    override suspend fun fetchTabs(): MovieTabs? {
        return null
    }
}