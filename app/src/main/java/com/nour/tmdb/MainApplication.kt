package com.nour.tmdb

import android.app.Application
import com.nour.core.common.di.commonModule
import com.nour.core.di.databaseModule
import com.nour.core.di.networkModule
import com.nour.data.di.moviesListDataModule
import com.nour.domain.di.moviesListDomainModule
import com.nour.ui.di.moviesListUiModule
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
            )
        }
    }
}
