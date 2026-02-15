package com.nour.core.di

import androidx.room.Room
import com.nour.core.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "movies_db"
        ).build()
    }

    single { get<AppDatabase>().favoriteMovieDao() }
}