package com.nour.ui.di

import com.nour.ui.viewmodel.MoviesListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val moviesListUiModule = module {
    viewModelOf(::MoviesListViewModel)
}