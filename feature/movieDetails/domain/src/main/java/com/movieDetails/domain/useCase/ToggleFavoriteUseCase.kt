package com.movieDetails.domain.useCase

import com.movieDetails.domain.repository.MovieDetailsRepository

class ToggleFavoriteUseCase(
    private val repository: MovieDetailsRepository
) {
    suspend operator fun invoke(movieId: Int, isFav: Boolean) = repository.updateFavoriteState(movieId, isFav)
}