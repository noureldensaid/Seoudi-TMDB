package com.movieDetails.ui.di

import com.movieDetails.ui.viewModel.MovieDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val movieDetailsUiModule = module {
    viewModelOf(::MovieDetailsViewModel)
}