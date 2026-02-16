package com.nour.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.nour.core.database.entity.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: MovieDetailsEntity)

    @Query("SELECT * FROM movie_details WHERE id = :movieId LIMIT 1")
    suspend fun getById(movieId: Int): MovieDetailsEntity?

    @Query("SELECT * FROM movie_details WHERE id = :movieId LIMIT 1")
    fun observeMovieDetails(movieId: Int): Flow<MovieDetailsEntity?>

    @Query("UPDATE movie_details SET isFavorite = :isFav, savedAtEpochMillis = :savedAt WHERE id = :movieId")
    suspend fun updateFavoriteState(
        movieId: Int,
        isFav: Boolean,
        savedAt: Long = System.currentTimeMillis()
    )

    @Transaction
    suspend fun upsertPreservingFavorite(incoming: MovieDetailsEntity) {
        val existing = getById(incoming.id)
        val merged = if (existing != null && existing.isFavorite) {
            incoming.copy(
                isFavorite = true,
                savedAtEpochMillis = existing.savedAtEpochMillis
            )
        } else {
            incoming
        }
        upsert(merged)
    }
}