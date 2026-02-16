package com.moviesList.ui.di

import com.moviesList.ui.viewmodel.MoviesListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val moviesListUiModule = module {
    viewModelOf(::MoviesListViewModel)
}