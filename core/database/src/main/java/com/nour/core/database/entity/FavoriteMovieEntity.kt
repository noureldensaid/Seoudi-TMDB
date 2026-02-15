package com.nour.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String?,
    val releaseDate: String?,
    val rating: Double?,
    val voteCount: Int?,
    val savedAtEpochMs: Long = System.currentTimeMillis()
)