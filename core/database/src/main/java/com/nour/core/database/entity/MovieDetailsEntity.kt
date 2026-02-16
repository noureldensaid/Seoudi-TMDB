package com.nour.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    @PrimaryKey val id: Int,
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val budget: Int? = null,
    val homepage: String? = null,
    val imdbId: String? = null,
    val genres: Map<Int, String> = emptyMap(),
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val isFavorite: Boolean = false,
    val savedAtEpochMillis: Long = System.currentTimeMillis()
)