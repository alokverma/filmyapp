package com.akki.filmyapp.api

import com.akki.filmyapp.home.domain.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/3/movie/popular?")
    suspend fun getTrendingMovies(@Query("api_key") apiKey: String = Constants.API_KEY): MovieList

    @GET("top_rated")
    suspend fun getTopRatingMovies(): String?

    @GET("trending")
    suspend fun getGenere(): String?

}