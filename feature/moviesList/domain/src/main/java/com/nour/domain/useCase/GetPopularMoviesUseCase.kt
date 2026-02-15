package com.nour.domain.useCase

import com.nour.domain.repository.MoviesListRepository

class GetPopularMoviesUseCase(
    private val moviesListRepository: MoviesListRepository
) {
    suspend operator fun invoke(page: String) = moviesListRepository.getPopularMovies(page)
}