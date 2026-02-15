package com.nour.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nour.core.common.result.ResponseState
import com.nour.core.common.util.Navigator
import com.nour.core.common.util.Paginator
import com.nour.domain.useCase.GetPopularMoviesUseCase
import com.nour.ui.model.MoviesListScreenEvents
import com.nour.ui.model.MoviesListScreenState
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val navigator: Navigator,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : ViewModel() {

    var state: MutableStateFlow<MoviesListScreenState> = MutableStateFlow(MoviesListScreenState())
        private set

    private val _errorFlow = Channel<ResponseState.Error>()
    val errorFlow = _errorFlow.receiveAsFlow()

    private val paginator = Paginator(
        initialLoadSize = 12,
        pageSize = 12,
        initialLoad = {
            getPopularMoviesUseCase(page = 1.toString())
        },
        loadMore = { cursor, _ ->
            getPopularMoviesUseCase(page = cursor.toString())
        },
        getItems = { page -> page.movies },
        getNextCursor = { page -> page.page.plus(1).toString() },
        getHasMore = { page -> page.page < page.totalPages },
        isEndReached = { endReached ->
            state.update { it.copy(endReached = endReached) }
        },
        distinctByKey = { it.id }
    )

    init {
        viewModelScope.launch {
            paginator.items.collect { result ->
                when (result) {
                    is ResponseState.Success -> state.update { it.copy(movies = result.data.toPersistentList()) }
                    is ResponseState.Error -> _errorFlow.send(result)
                }
            }
        }
        onEvent(MoviesListScreenEvents.OnInitialLoad)
    }

    fun onEvent(event: MoviesListScreenEvents) {
        when (event) {
            MoviesListScreenEvents.OnInitialLoad -> loadInitial()
            MoviesListScreenEvents.OnLoadMore -> loadNext()
            MoviesListScreenEvents.OnRefresh -> loadInitial()
            MoviesListScreenEvents.OnRetry -> loadInitial()
            is MoviesListScreenEvents.OnNavigateToDetails -> navigateToDetails(event.movieId)
        }
    }

    private fun loadInitial() {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            paginator.loadInitial(force = true)
            state.update { it.copy(isLoading = false) }
        }
    }

    private fun loadNext() {
        viewModelScope.launch {
            state.update { it.copy(isAppending = true) }
            paginator.loadNextPage()
            state.update { it.copy(isAppending = false) }
        }
    }

    private fun navigateToDetails(movieId: Int) {
    }
}