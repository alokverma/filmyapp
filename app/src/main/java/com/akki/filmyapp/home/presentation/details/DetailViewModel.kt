package com.akki.filmyapp.home.presentation.details

import androidx.lifecycle.ViewModel
import com.akki.filmyapp.home.domain.getmoviesusecase.MovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class DetailViewModel(
    val movieDetailUseCase: MovieDetailUseCase
) : ViewModel() {

    fun fetchMovieDetail() {
        movieDetailUseCase()
    }
}
