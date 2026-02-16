package com.movieDetails.ui.model

import androidx.compose.runtime.Stable
import com.movieDetails.domain.model.MovieDetailsModel

@Stable
data class MovieDetailsScreenState(
    val movie: MovieDetailsModel = MovieDetailsModel(),
    val isLoading: Boolean = true,
)
