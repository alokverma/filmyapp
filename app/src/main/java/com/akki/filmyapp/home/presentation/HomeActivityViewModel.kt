package com.akki.filmyapp.home.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akki.filmyapp.home.domain.repository.IHomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val homeRepository: IHomeRepository
): ViewModel() {

    fun fetchTrendingVideos() {
        viewModelScope.launch {
            homeRepository.getTrendingMovies()
        }
    }

}