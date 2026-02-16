package com.movieDetails.data.remote

import com.movieDetails.data.remote.dto.MovieDetailsDto
import com.nour.core.common.result.ResponseState

interface MovieDetailsRemoteDataSource {
    suspend fun getMovieDetails(movieId: Int): ResponseState<MovieDetailsDto>
}