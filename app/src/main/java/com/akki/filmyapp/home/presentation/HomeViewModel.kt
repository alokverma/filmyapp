package com.akki.filmyapp.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akki.filmyapp.basemodel.Resource
import com.akki.filmyapp.home.domain.model.MovieList
import com.akki.filmyapp.home.domain.repository.IHomeRepository
import com.akki.filmyapp.logging.ILogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val homeRepository: IHomeRepository,
    private val logger: ILogger
) : ViewModel() {

    private val _trendingMovies = MutableLiveData<MovieList?>()
    val trendingMovies: MutableLiveData<MovieList?>
        get() = _trendingMovies

    fun fetchTrendingVideos() {
        viewModelScope.launch {
            homeRepository.getTrendingMovies().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _trendingMovies.value = result.data
                    }

                    is Resource.Loading -> {
                        logger.logMessage(message = "loading")

                    }

                    is Resource.Error -> {
                        logger.logMessage(message = "Something went wrong")
                        _trendingMovies.value = null
                    }
                }
            }
        }
    }

}