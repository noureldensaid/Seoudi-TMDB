package com.moviesList.domain.useCase

import com.moviesList.domain.repository.MoviesListRepository

class GetPopularMoviesUseCase(
    private val moviesListRepository: MoviesListRepository
) {
    suspend operator fun invoke(page: String) = moviesListRepository.getPopularMovies(page)
}