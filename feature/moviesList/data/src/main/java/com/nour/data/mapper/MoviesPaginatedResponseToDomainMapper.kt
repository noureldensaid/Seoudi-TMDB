package com.nour.data.mapper

import com.nour.core.common.base.BaseMapper
import com.nour.data.remote.dto.MoviesPaginatedResponse
import com.nour.domain.model.MovieModel
import com.nour.domain.model.MoviesPage

class MoviesPaginatedResponseToDomainMapper : BaseMapper<MoviesPaginatedResponse, MoviesPage> {

    override fun map(from: MoviesPaginatedResponse): MoviesPage {
        return MoviesPage(
            page = from.page ?: 1,
            movies = from.movies?.mapNotNull { movieDto ->
                MovieModel(
                    id = movieDto?.id ?: 0,
                    overview = movieDto?.overview.orEmpty(),
                    posterPath = movieDto?.posterPath.orEmpty(),
                    backDropPath = movieDto?.backdropPath.orEmpty(),
                    releaseDate = movieDto?.releaseDate.orEmpty(),
                    originalTitle = movieDto?.originalTitle.orEmpty(),
                    voteAverage = movieDto?.voteAverage ?: 0.0,
                    voteCount = movieDto?.voteCount ?: 0
                )
            } ?: emptyList(),
            totalPages = from.totalPages ?: 1,
            totalResults = from.totalResults ?: 0
        )
    }
}
