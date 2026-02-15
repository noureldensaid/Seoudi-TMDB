package com.nour.domain.di

import com.nour.domain.useCase.GetPopularMoviesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val moviesListDomainModule = module {
    factoryOf(::GetPopularMoviesUseCase)
}