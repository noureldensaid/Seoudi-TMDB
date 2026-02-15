package com.nour.data.repository

import com.nour.core.common.result.ResponseState
import com.nour.data.mapper.MoviesPaginatedResponseToDomainMapper
import com.nour.data.remote.MoviesListRemoteDataSource
import com.nour.domain.model.MoviesPage
import com.nour.domain.repository.MoviesListRepository

class MoviesListRepositoryImpl(
    private val moviesListRemoteDataSource: MoviesListRemoteDataSource,
    private val paginatedResponseToDomainMapper: MoviesPaginatedResponseToDomainMapper,
) : MoviesListRepository {

    override suspend fun getPopularMovies(page: String): ResponseState<MoviesPage> {
        return when (val response = moviesListRemoteDataSource.getPopularMovies(page)) {
            is ResponseState.Error -> ResponseState.Error(response.error, response.errorBody)
            is ResponseState.Success -> {
                val moviesPage = paginatedResponseToDomainMapper.map(response.data)
                ResponseState.Success(moviesPage)
            }
        }
    }
}