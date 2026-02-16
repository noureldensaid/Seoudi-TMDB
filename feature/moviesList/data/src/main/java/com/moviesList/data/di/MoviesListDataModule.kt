package com.moviesList.data.di

import com.moviesList.data.mapper.MoviesPaginatedResponseToDomainMapper
import com.moviesList.data.remote.MoviesListRemoteDataSource
import com.moviesList.data.remote.MoviesListRemoteDataSourceImpl
import com.moviesList.data.repository.MoviesListRepositoryImpl
import com.moviesList.domain.repository.MoviesListRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val moviesListDataModule = module {
    singleOf(::MoviesListRemoteDataSourceImpl) { bind<MoviesListRemoteDataSource>() }
    factoryOf(::MoviesPaginatedResponseToDomainMapper)
    singleOf(::MoviesListRepositoryImpl) { bind<MoviesListRepository>() }
}