package com.moviesList.domain.di

import com.moviesList.domain.useCase.GetPopularMoviesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val moviesListDomainModule = module {
    factoryOf(::GetPopularMoviesUseCase)
}