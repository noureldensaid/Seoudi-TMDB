package com.moviesList.data.remote

import com.moviesList.data.remote.dto.MoviesPaginatedResponse
import com.nour.core.common.result.ResponseState

interface MoviesListRemoteDataSource {
    suspend fun getPopularMovies(page: String): ResponseState<MoviesPaginatedResponse>
}