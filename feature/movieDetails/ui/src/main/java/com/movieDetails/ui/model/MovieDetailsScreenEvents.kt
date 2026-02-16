package com.movieDetails.ui.model

sealed interface MovieDetailsScreenEvents {
    data object GetMovieDetails : MovieDetailsScreenEvents
    data class OnToggleFavorite(val movieId: Int, val isFavorite: Boolean) : MovieDetailsScreenEvents
}


