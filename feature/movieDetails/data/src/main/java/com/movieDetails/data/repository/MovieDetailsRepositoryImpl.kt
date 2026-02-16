package com.movieDetails.data.repository

import com.movieDetails.data.mapper.MovieDetailsDtoToEntityMapper
import com.movieDetails.data.mapper.MovieDetailsEntityToModelMapper
import com.movieDetails.data.remote.MovieDetailsRemoteDataSource
import com.movieDetails.domain.model.MovieDetailsModel
import com.movieDetails.domain.repository.MovieDetailsRepository
import com.nour.core.common.result.ResponseState
import com.nour.core.database.dao.MovieDetailsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class MovieDetailsRepositoryImpl(
    private val remoteDataSource: MovieDetailsRemoteDataSource,
    private val movieDetailsDao: MovieDetailsDao,
    private val movieDetailsDtoToEntityMapper: MovieDetailsDtoToEntityMapper,
    private val movieDetailsEntityToModelMapper: MovieDetailsEntityToModelMapper
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Int): ResponseState<Flow<MovieDetailsModel>> {
        return when (val response = remoteDataSource.getMovieDetails(movieId)) {
            is ResponseState.Error -> ResponseState.Error(response.error, response.errorBody)
            is ResponseState.Success -> {
                val movieDetailsResponse = response.data
                val movieDetailsEntity = movieDetailsDtoToEntityMapper.map(movieDetailsResponse)
                movieDetailsDao.upsertPreservingFavorite(movieDetailsEntity)
                val movieDetailsModelFlow =
                    movieDetailsDao.observeMovieDetails(movieId).mapNotNull {
                        it?.let { movieDetailsEntityToModelMapper.map(it) }
                    }
                ResponseState.Success(movieDetailsModelFlow)
            }
        }
    }

    override suspend fun updateFavoriteState(movieId: Int, isFav: Boolean) {
        movieDetailsDao.updateFavoriteState(movieId, isFav)
    }
}