package com.movieDetails.data.mapper

import com.movieDetails.data.utils.formatMoney
import com.movieDetails.data.utils.formatRuntime
import com.movieDetails.domain.model.MovieDetailsModel
import com.nour.core.common.base.BaseMapper
import com.nour.core.database.entity.MovieDetailsEntity

class MovieDetailsEntityToModelMapper : BaseMapper<MovieDetailsEntity, MovieDetailsModel> {

    override fun map(from: MovieDetailsEntity): MovieDetailsModel {
        return MovieDetailsModel(
            id = from.id,
            adult = from.adult,
            backdropPath = from.backdropPath,
            budget = formatMoney(from.budget),
            homepage = from.homepage,
            imdbId = from.imdbId,
            originalLanguage = from.originalLanguage,
            originalTitle = from.originalTitle.orEmpty(),
            overview = from.overview.orEmpty(),
            posterPath = from.posterPath,
            releaseDate = from.releaseDate.orEmpty(),
            revenue = formatMoney(from.revenue),
            runtime = formatRuntime(from.runtime),
            status = from.status.orEmpty(),
            tagline = from.tagline,
            title = from.title,
            video = from.video,
            voteAverage = "%.1f".format(from.voteAverage ?: 0.0),
            voteCount = from.voteCount.toString(),
            isFavorite = from.isFavorite,
            genres = from.genres,
        )
    }
}