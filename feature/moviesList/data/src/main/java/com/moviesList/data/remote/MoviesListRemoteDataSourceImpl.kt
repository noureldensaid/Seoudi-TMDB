package com.moviesList.data.remote

import com.moviesList.data.remote.dto.MoviesPaginatedResponse
import com.nour.core.common.result.ResponseState
import com.nour.core.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MoviesListRemoteDataSourceImpl(
    private val client: HttpClient
) : MoviesListRemoteDataSource {

    override suspend fun getPopularMovies(page: String): ResponseState<MoviesPaginatedResponse> = safeApiCall {
        client.get("popular") {
            url.parameters.append("page", page)
        }.body()
    }
}