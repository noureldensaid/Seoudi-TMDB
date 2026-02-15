package com.nour.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nour.core.ui.theme.AppTheme
import com.nour.domain.model.MovieModel

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: MovieModel,
    index: Int,
    onClick: () -> Unit,
) {
    if (index % 3 == 0) {
        FeaturedMovieCard(
            modifier = modifier,
            movie = movie,
            onClick = onClick,
        )
    } else {
        NormalMovieCard(
            modifier = modifier,
            movie = movie,
            onClick = onClick,
        )
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    AppTheme {
        MovieCard(
            movie = MovieModel(
                id = 1,
                overview = "This is a movie overview",
                posterPath = "",
                releaseDate = "2022-01-01",
                originalTitle = "The Movie Title",
                voteAverage = 7.5,
                voteCount = 120,
                isFavorite = false,
                backDropPath = ""
            ),
            index = 1,
            onClick = {},
        )
    }
}