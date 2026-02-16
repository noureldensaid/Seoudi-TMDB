package com.movieDetails.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movieDetails.domain.model.MovieDetailsModel
import com.movieDetails.ui.components.MovieDetailsBody
import com.movieDetails.ui.model.MovieDetailsScreenEvents
import com.movieDetails.ui.model.MovieDetailsScreenState
import com.nour.core.ui.components.asyncImage.PosterImage
import com.nour.core.ui.components.button.FavoriteButton
import com.nour.core.ui.components.icons.BackIcon

@Composable
fun MovieDetailsScreenRoot(
    state: MovieDetailsScreenState,
    onEvent: (MovieDetailsScreenEvents) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackIcon { }

                FavoriteButton(
                    isFavorite = state.movie.isFavorite,
                    onToggle = {
                        onEvent(
                            MovieDetailsScreenEvents.OnToggleFavorite(
                                state.movie.id,
                                state.movie.isFavorite.not()
                            )
                        )
                    }
                )
            }

            PosterImage(
                posterPath = state.movie.backdropPath,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.5f
            )

            PosterImage(
                posterPath = state.movie.posterPath,
                modifier = Modifier
                    .size(200.dp, 300.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .align(Alignment.BottomCenter),
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.surface.copy(0.85f),
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(0f, Float.POSITIVE_INFINITY)
                        )
                    )
            )
        }
        MovieDetailsBody(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            movie = state.movie
        )
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
