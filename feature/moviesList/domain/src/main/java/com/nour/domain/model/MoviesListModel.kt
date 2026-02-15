package com.nour.domain.model

data class MoviesPage(
    val page: Int,
    val movies: List<MovieModel>,
    val totalPages: Int,
    val totalResults: Int
)

data class MovieModel(
    val id: Int,
    val overview: String,
    val posterPath: String,
    val backDropPath: String,
    val releaseDate: String,
    val originalTitle: String,
    val voteAverage: Double,
    val voteCount: Int,
    val isFavorite: Boolean = false
)
