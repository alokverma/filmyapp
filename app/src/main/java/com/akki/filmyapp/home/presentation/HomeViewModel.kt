package com.akki.filmyapp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akki.filmyapp.basemodel.Resource
import com.akki.filmyapp.home.domain.model.Genre
import com.akki.filmyapp.home.domain.model.HomeState
import com.akki.filmyapp.home.domain.model.MovieList
import com.akki.filmyapp.home.domain.model.MovieTabs
import com.akki.filmyapp.home.domain.repository.IHomeRepository
import com.akki.filmyapp.logging.ILogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: IHomeRepository,
    private val logger: ILogger
) : ViewModel() {

    private val _homeStateUIModel = MutableStateFlow<HomeState?>(null)
    val homeStateUIModel: StateFlow<HomeState?>
        get() = _homeStateUIModel.asStateFlow()

    fun fetchHomeViewData() {
        viewModelScope.launch {
            combine(
                homeRepository.getTrendingMovies(),
                homeRepository.fetchTabs()
            ) { trending: Resource<MovieList>, tabs: List<MovieTabs> ->
                return@combine HomeState(tabs, trending.data)
            }.collect { uiState ->
                _homeStateUIModel.value = uiState
            }
        }
    }
}