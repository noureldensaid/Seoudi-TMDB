package com.moviesList.domain.repository

import com.moviesList.domain.model.MoviesPage
import com.nour.core.common.result.ResponseState

interface MoviesListRepository {
    suspend fun getPopularMovies(page: String): ResponseState<MoviesPage>
}