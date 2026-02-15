package com.nour.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nour.core.ui.components.chip.RatingChip
import com.nour.core.ui.components.text.DefaultText
import com.nour.core.ui.extensions.skipInteraction
import com.nour.core.ui.theme.AppTheme
import com.nour.domain.model.MovieModel

@Composable
fun NormalMovieCard(
    modifier: Modifier = Modifier,
    movie: MovieModel,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        interactionSource = skipInteraction(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            PosterImage(
                posterPath = movie.posterPath,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                DefaultText(
                    text = movie.originalTitle,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                )

                Spacer(Modifier.height(6.dp))

                DefaultText(
                    text = "Release • ${movie.releaseDate}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(10.dp))

                RatingChip(
                    leadingIcon = { Text("🍿", style = MaterialTheme.typography.labelLarge) },
                    rating = movie.voteAverage,
                    voteCount = movie.voteCount
                )

                Spacer(Modifier.height(8.dp))

                DefaultText(
                    text = "Year • ${movie.releaseDate}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview
@Composable
fun NormalMovieCardPreview() {
    AppTheme {
        NormalMovieCard(
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
            onClick = {},
        )
    }
}