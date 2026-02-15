package com.nour.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nour.core.database.entity.FavoriteMovieEntity

@Dao
interface FavoriteMovieDao {

    @Query("SELECT id FROM favorite_movies")
    suspend fun getFavoriteIds(): List<Int>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId LIMIT 1")
    suspend fun getById(movieId: Int): FavoriteMovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: FavoriteMovieEntity)

    @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    suspend fun deleteById(movieId: Int)

    suspend fun toggleFavState(movie: FavoriteMovieEntity) {
        val existing = getById(movie.id)
        if (existing == null) insert(movie) else deleteById(movie.id)
    }
}