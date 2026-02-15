package com.nour.core.di

import com.nour.core.network.HttpClientFactory
import com.nour.core.util.connectivityObserver.AndroidConnectivityObserver
import com.nour.core.util.connectivityObserver.ConnectivityObserver
import io.ktor.client.HttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {

    single { HttpClientFactory(androidContext()) }

    single<HttpClient>(createdAtStart = true) {
        get<HttpClientFactory>().build()
    }

    singleOf(::AndroidConnectivityObserver) { bind<ConnectivityObserver>() }

}