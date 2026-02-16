package com.movieDetails.domain.di

import com.movieDetails.domain.useCase.GetMovieDetailsUseCase
import com.movieDetails.domain.useCase.ToggleFavoriteUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val movieDetailsDomainModule = module {
    factoryOf(::GetMovieDetailsUseCase)
    factoryOf(::ToggleFavoriteUseCase)
}

