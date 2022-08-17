package com.akki.filmyapp.home.domain.model

import com.google.gson.annotations.SerializedName

data class MovieList(@SerializedName("page")
                     val page: Int = 0,
                     @SerializedName("total_pages")
                     val totalPages: Int = 0,
                     @SerializedName("results")
                     val results: List<MovieItem>?,
                     @SerializedName("total_results")
                     val totalResults: Int = 0)