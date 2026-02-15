package com.nour.ui.model

import androidx.compose.runtime.Stable
import com.nour.domain.model.MovieModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class MoviesListScreenState(
    val movies: PersistentList<MovieModel> = persistentListOf(),
    val isLoading: Boolean = true,
    val isAppending: Boolean = false,
    val endReached : Boolean = false
)
