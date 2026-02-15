package com.nour.domain.repository

import com.nour.core.common.result.ResponseState
import com.nour.domain.model.MoviesPage

interface MoviesListRepository {
    suspend fun getPopularMovies(page: String): ResponseState<MoviesPage>
}