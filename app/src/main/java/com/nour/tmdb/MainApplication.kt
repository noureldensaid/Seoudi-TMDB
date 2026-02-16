package com.nour.tmdb

import android.app.Application
import com.movieDetails.data.di.movieDetailsDataModule
import com.movieDetails.domain.di.movieDetailsDomainModule
import com.movieDetails.ui.di.movieDetailsUiModule
import com.moviesList.data.di.moviesListDataModule
import com.moviesList.domain.di.moviesListDomainModule
import com.moviesList.ui.di.moviesListUiModule
import com.nour.core.common.di.commonModule
import com.nour.core.di.databaseModule
import com.nour.core.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                networkModule,
                databaseModule,
                commonModule,

                moviesListDataModule,
                moviesListDomainModule,
                moviesListUiModule,

                movieDetailsDataModule,
                movieDetailsDomainModule,
                movieDetailsUiModule
            )
        }
    }
}
