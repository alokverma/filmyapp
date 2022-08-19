package com.akki.filmyapp.api

import com.akki.filmyapp.home.domain.model.Genre
import com.akki.filmyapp.home.domain.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/3/movie/{type}?")
    suspend fun getMovies(@Path(value = "type", encoded = true) type: String, @Query("api_key") apiKey: String = Constants.API_KEY): MovieList

    @GET("top_rated")
    suspend fun getTopRatingMovies(): String?

    @GET("/3/genre/movie/list?")
    suspend fun getGenere(@Query("api_key") apiKey: String = Constants.API_KEY): Genre?

}