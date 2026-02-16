package com.movieDetails.domain.useCase

import com.movieDetails.domain.repository.MovieDetailsRepository

class GetMovieDetailsUseCase(
    private val repository: MovieDetailsRepository
) {
    suspend operator fun invoke(movieId: Int) = repository.getMovieDetails(movieId)
}