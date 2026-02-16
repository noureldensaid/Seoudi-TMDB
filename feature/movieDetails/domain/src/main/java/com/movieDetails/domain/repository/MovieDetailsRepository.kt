package com.movieDetails.domain.repository

import com.movieDetails.domain.model.MovieDetailsModel
import com.nour.core.common.result.ResponseState
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): ResponseState<Flow<MovieDetailsModel>>
    suspend fun updateFavoriteState(movieId: Int, isFav: Boolean)
}
