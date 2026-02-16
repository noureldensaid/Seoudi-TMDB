package com.moviesList.data.mapper

import com.moviesList.data.remote.dto.MoviesPaginatedResponse
import com.moviesList.domain.model.MovieModel
import com.moviesList.domain.model.MoviesPage
import com.nour.core.common.base.BaseMapper

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
                    voteAverage = "%.1f".format(movieDto?.voteAverage ?: 0.0),
                    voteCount = movieDto?.voteCount.toString()
                )
            } ?: emptyList(),
            totalPages = from.totalPages ?: 1,
            totalResults = from.totalResults ?: 0
        )
    }
}
