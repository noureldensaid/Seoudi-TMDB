package com.nour.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nour.core.database.dao.FavoriteMovieDao
import com.nour.core.database.entity.FavoriteMovieEntity


@Database(
    entities = [
        FavoriteMovieEntity::class,
    ],
    version = 1,
    exportSchema = true
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}
