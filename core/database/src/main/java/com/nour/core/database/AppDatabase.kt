package com.nour.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nour.core.database.dao.MovieDetailsDao
import com.nour.core.database.entity.MovieDetailsEntity
import com.nour.core.database.typeConverters.Converters


@Database(
    entities = [MovieDetailsEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): MovieDetailsDao
}
