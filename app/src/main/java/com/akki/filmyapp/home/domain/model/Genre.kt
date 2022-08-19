package com.akki.filmyapp.home.domain.model

import com.google.gson.annotations.SerializedName

data class Genre(@SerializedName("genres")
                 val genres: List<GenresItem>?)