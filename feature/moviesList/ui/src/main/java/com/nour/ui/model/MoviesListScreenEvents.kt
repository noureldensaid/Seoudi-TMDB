package com.nour.ui.model

sealed interface MoviesListScreenEvents {
    data object OnInitialLoad : MoviesListScreenEvents
    data object OnRetry : MoviesListScreenEvents
    data object OnRefresh : MoviesListScreenEvents
    data object OnLoadMore : MoviesListScreenEvents
    data class  OnNavigateToDetails(val movieId: Int) : MoviesListScreenEvents
}