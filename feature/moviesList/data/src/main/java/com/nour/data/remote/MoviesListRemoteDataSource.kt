package com.nour.data.remote

import com.nour.core.common.result.ResponseState
import com.nour.data.remote.dto.MoviesPaginatedResponse

interface MoviesListRemoteDataSource {
    suspend fun getPopularMovies(page: String): ResponseState<MoviesPaginatedResponse>
}