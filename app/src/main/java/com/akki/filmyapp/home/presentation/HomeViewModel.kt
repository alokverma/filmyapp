package com.akki.filmyapp.home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akki.filmyapp.basemodel.Resource
import com.akki.filmyapp.home.domain.getmoviesusecase.FetchMoviesUseCase
import com.akki.filmyapp.home.domain.model.HomeState
import com.akki.filmyapp.home.domain.model.MovieItem
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
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val logger: ILogger
) : ViewModel() {

    private val _homeStateUIModel = MutableStateFlow<HomeState?>(null)
    val homeStateUIModel: StateFlow<HomeState?>
        get() = _homeStateUIModel.asStateFlow()

    private val _moviesData = MutableStateFlow<MovieList?>(null)
    val moviesData: StateFlow<MovieList?>
        get() = _moviesData.asStateFlow()

    private val _homeUiState = mutableStateOf(HomeUIState())
    val homeUiState: State<HomeUIState> = _homeUiState


//    fun fetchHomeViewData() {
//        viewModelScope.launch {
//            homeRepository.getMovies("popular").
//            combine(
//                homeRepository.fetchTabs()
//            ) { trending: Resource<MovieList>, tabs: List<MovieTabs> ->
//                HomeState(tabs, trending.data)
//            }.stateIn(viewModelScope).collect {
//                _homeStateUIModel.value = it
//            }
//        }
//    }


    fun fetchMovies(type: String) {
        fetchMoviesUseCase(type).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _homeUiState.value = HomeUIState(isLoading = true)
                }

                is Resource.Success -> {
                    _homeUiState.value = HomeUIState(result.data?.results ?: emptyList(), false)
                }

                is Resource.Error -> {
                    _homeUiState.value = HomeUIState(error = "Something went wrong")
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class HomeUIState(
    val movieResult: List<MovieItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)