package com.nour.data.di

import com.nour.data.mapper.MoviesPaginatedResponseToDomainMapper
import com.nour.data.remote.MoviesListRemoteDataSource
import com.nour.data.remote.MoviesListRemoteDataSourceImpl
import com.nour.data.repository.MoviesListRepositoryImpl
import com.nour.domain.repository.MoviesListRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val moviesListDataModule = module {
    singleOf(::MoviesListRemoteDataSourceImpl) { bind<MoviesListRemoteDataSource>() }
    factoryOf(::MoviesPaginatedResponseToDomainMapper)
    singleOf(::MoviesListRepositoryImpl) { bind<MoviesListRepository>() }
}