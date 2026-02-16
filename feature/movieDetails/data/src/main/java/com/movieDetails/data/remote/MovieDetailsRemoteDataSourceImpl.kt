package com.movieDetails.data.remote

import com.movieDetails.data.remote.dto.MovieDetailsDto
import com.nour.core.common.result.ResponseState
import com.nour.core.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieDetailsRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : MovieDetailsRemoteDataSource {

    override suspend fun getMovieDetails(movieId: Int): ResponseState<MovieDetailsDto> =
        safeApiCall {
            httpClient.get("$movieId").body()
        }
}