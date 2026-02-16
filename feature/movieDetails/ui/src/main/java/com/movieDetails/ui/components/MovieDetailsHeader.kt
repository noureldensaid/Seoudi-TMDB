package com.movieDetails.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.nour.core.ui.components.asyncImage.PosterImage
import com.nour.core.ui.components.button.FavoriteButton
import com.nour.core.ui.components.icons.BackIcon

@Composable
fun MovieDetailsHeader(
    modifier: Modifier = Modifier,
    movie: MovieDetailsModel,
    onToggleFavorite: () -> Unit,
) {
    Box(modifier) {
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
                isFavorite = movie.isFavorite,
                onToggle = onToggleFavorite
            )
        }

        PosterImage(
            posterPath = movie.backdropPath,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )

        PosterImage(
            posterPath = movie.posterPath,
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
}

@Preview
@Composable
private fun MovieDetailsHeaderPreview() {
    MovieDetailsHeader(
        movie = MovieDetailsModel(
            originalTitle = "The Movie Title",
            title = "The Movie Title",
        ),
        onToggleFavorite = {}
    )
}