package com.movieDetails.data.mapper

import com.movieDetails.data.remote.dto.MovieDetailsDto
import com.nour.core.common.base.BaseMapper
import com.nour.core.database.entity.MovieDetailsEntity

class MovieDetailsDtoToEntityMapper : BaseMapper<MovieDetailsDto, MovieDetailsEntity> {

    override fun map(from: MovieDetailsDto): MovieDetailsEntity {
        return MovieDetailsEntity(
            id = from.id ?: -1,
            adult = from.adult,
            backdropPath = from.backdropPath,
            budget = from.budget,
            homepage = from.homepage,
            imdbId = from.imdbId,
            originalLanguage = from.originalLanguage,
            originalTitle = from.originalTitle,
            overview = from.overview,
            posterPath = from.posterPath,
            releaseDate = from.releaseDate,
            revenue = from.revenue,
            runtime = from.runtime,
            status = from.status,
            tagline = from.tagline,
            title = from.title,
            video = from.video,
            voteAverage = from.voteAverage,
            voteCount = from.voteCount,
            genres = from.genres
                .orEmpty()
                .mapNotNull { g ->
                    val gid = g?.id
                    val name = g?.name
                    if (gid != null && !name.isNullOrBlank()) gid to name else null
                }
                .toMap(),
            isFavorite = false,
            savedAtEpochMillis = System.currentTimeMillis()
        )
    }
}