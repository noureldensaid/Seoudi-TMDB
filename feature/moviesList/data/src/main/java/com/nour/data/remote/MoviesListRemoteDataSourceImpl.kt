package com.nour.data.remote

import com.nour.core.common.result.ResponseState
import com.nour.core.network.safeApiCall
import com.nour.data.remote.dto.MoviesPaginatedResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MoviesListRemoteDataSourceImpl(
    private val client: HttpClient
) : MoviesListRemoteDataSource {

    override suspend fun getPopularMovies(page: String, ): ResponseState<MoviesPaginatedResponse> = safeApiCall {
        client.get("popular") {
            url.parameters.append("page", page)
        }.body()
    }
}