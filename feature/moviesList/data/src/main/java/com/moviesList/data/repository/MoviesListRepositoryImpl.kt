package com.moviesList.data.repository

import com.moviesList.data.mapper.MoviesPaginatedResponseToDomainMapper
import com.moviesList.data.remote.MoviesListRemoteDataSource
import com.moviesList.domain.model.MoviesPage
import com.moviesList.domain.repository.MoviesListRepository
import com.nour.core.common.result.ResponseState

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