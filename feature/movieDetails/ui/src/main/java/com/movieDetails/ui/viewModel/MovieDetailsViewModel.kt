package com.movieDetails.ui.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.movieDetails.domain.useCase.GetMovieDetailsUseCase
import com.movieDetails.domain.useCase.ToggleFavoriteUseCase
import com.movieDetails.ui.model.MovieDetailsScreenEvents
import com.movieDetails.ui.model.MovieDetailsScreenState
import com.movieDetails.ui.navigation.MovieDetailsRoute
import com.nour.core.common.result.ResponseState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state = MutableStateFlow(MovieDetailsScreenState())
        private set

    private val _errorFlow = Channel<ResponseState.Error>()
    val errorFlow = _errorFlow.receiveAsFlow()

    init {
        getMovieDetails()
    }

    fun onEvent(event: MovieDetailsScreenEvents) {
        when (event) {
            is MovieDetailsScreenEvents.GetMovieDetails -> getMovieDetails()
            is MovieDetailsScreenEvents.OnToggleFavorite -> toggleFavorite(event.movieId, event.isFavorite)
        }
    }


    private fun getMovieDetails() {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            val movieId = savedStateHandle.toRoute<MovieDetailsRoute>().id
            when (val movieDetailsResponse = getMovieDetailsUseCase(movieId)) {
                is ResponseState.Error -> {
                    state.update { it.copy(isLoading = false) }
                    _errorFlow.send(movieDetailsResponse)
                }
                is ResponseState.Success -> {
                    movieDetailsResponse.data.collectLatest { movieDetails ->
                        state.update {
                            it.copy(movie = movieDetails, isLoading = false)
                        }
                    }
                }
            }
        }
    }

    private fun toggleFavorite(movieId: Int, isFav: Boolean) {
        viewModelScope.launch {
            toggleFavoriteUseCase(movieId, isFav)
        }
    }
}