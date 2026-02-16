package com.movieDetails.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movieDetails.domain.model.MovieDetailsModel
import com.movieDetails.ui.components.MovieDetailsBody
import com.movieDetails.ui.components.MovieDetailsHeader
import com.movieDetails.ui.model.MovieDetailsScreenEvents
import com.movieDetails.ui.model.MovieDetailsScreenState
import com.nour.core.ui.extensions.isLandscape

@Composable
fun MovieDetailsScreenRoot(
    state: MovieDetailsScreenState,
    onEvent: (MovieDetailsScreenEvents) -> Unit,
    modifier: Modifier = Modifier
) {

    val toggleFavorite = {
        onEvent(
            MovieDetailsScreenEvents.OnToggleFavorite(
                state.movie.id,
                state.movie.isFavorite.not()
            )
        )
    }

    if (isLandscape()) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) {
            MovieDetailsHeader(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                movie = state.movie,
                onToggleFavorite = toggleFavorite
            )
            MovieDetailsBody(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                movie = state.movie
            )
        }
    } else {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MovieDetailsHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                movie = state.movie,
                onToggleFavorite = toggleFavorite
            )
            MovieDetailsBody(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                movie = state.movie
            )
        }
    }
}

@Preview
@Composable
private fun MovieDetailsScreenRootPreview() {
    MovieDetailsScreenRoot(
        state = MovieDetailsScreenState(
            movie = MovieDetailsModel(
                id = 1,
                adult = false,
                backdropPath = "/sample_backdrop.jpg",
                homepage = "https://example.com",
                imdbId = "tt1234567",
                originalLanguage = "en",
                originalTitle = "Sample Movie",
                overview = "This is a sample movie used for UI preview purposes.",
                posterPath = "/sample_poster.jpg",
                releaseDate = "2024-01-01",
                revenue = "450000000",
                runtime = "120",
                spokenLanguagesJson = "[]",
                status = "Released",
                tagline = "An epic journey begins.",
                title = "Sample Movie",
                video = false,
                isFavorite = true
            ),
            isLoading = false
        ),
        onEvent = {}
    )
}
