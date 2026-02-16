package com.movieDetails.data.di

import com.movieDetails.data.mapper.MovieDetailsDtoToEntityMapper
import com.movieDetails.data.mapper.MovieDetailsEntityToModelMapper
import com.movieDetails.data.remote.MovieDetailsRemoteDataSource
import com.movieDetails.data.remote.MovieDetailsRemoteDataSourceImpl
import com.movieDetails.data.repository.MovieDetailsRepositoryImpl
import com.movieDetails.domain.repository.MovieDetailsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val movieDetailsDataModule = module {
    singleOf(::MovieDetailsRemoteDataSourceImpl) { bind<MovieDetailsRemoteDataSource>() }
    factoryOf(::MovieDetailsEntityToModelMapper)
    factoryOf(::MovieDetailsDtoToEntityMapper)
    singleOf(::MovieDetailsRepositoryImpl) { bind<MovieDetailsRepository>() }
}
