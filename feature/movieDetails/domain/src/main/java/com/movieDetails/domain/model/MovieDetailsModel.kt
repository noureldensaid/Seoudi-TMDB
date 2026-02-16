package com.movieDetails.domain.model

data class MovieDetailsModel(
    val id: Int = -1,
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val budget: String = "",
    val genres: Map<Int, String> = emptyMap(),
    val homepage: String? = null,
    val imdbId: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String = "",
    val overview: String = "",
    val posterPath: String? = null,
    val releaseDate: String = "",
    val revenue: String = "",
    val runtime: String = "",
    val spokenLanguagesJson: String? = null,
    val status: String = "",
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: String = "",
    val voteCount: String = "",
    val isFavorite: Boolean = false
)
