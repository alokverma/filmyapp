package com.akki.filmyapp.home.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.akki.filmyapp.home.domain.getmoviesusecase.MovieDetailUseCase
import com.akki.filmyapp.home.domain.model.MovieItem
import com.akki.filmyapp.home.presentation.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val movieDetailUseCase: MovieDetailUseCase
) : ViewModel() {

    private val _detailUIState = mutableStateOf(DetailUiState())
    val detailUiState: State<DetailUiState> = _detailUIState

    init {
        fetchMovieDetail()
    }

    private fun fetchMovieDetail() {
        movieDetailUseCase()
    }

    data class DetailUiState(
        val movieResult: MovieItem? = null,
        val isLoading: Boolean = false,
        val error: String = ""
    )
}
