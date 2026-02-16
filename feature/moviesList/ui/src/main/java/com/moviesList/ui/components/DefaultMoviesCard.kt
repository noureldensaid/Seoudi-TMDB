package com.moviesList.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviesList.domain.model.MovieModel
import com.nour.core.ui.components.asyncImage.PosterImage
import com.nour.core.ui.components.text.DefaultText
import com.nour.core.ui.extensions.skipInteraction
import com.nour.core.ui.theme.AppTheme

@Composable
fun DefaultMoviesCard(
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
        Row(modifier = Modifier.height(170.dp)) {
            PosterImage(
                posterPath = movie.posterPath,
                modifier = Modifier
                    .width(130.dp)
                    .fillMaxHeight()
            )
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                DefaultText(
                    text = movie.originalTitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                )

                RateCard(
                    voteAverage = movie.voteAverage,
                    voteCount = movie.voteCount
                )

                DefaultText(
                    text = "Release • ${movie.releaseDate}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultMoviesCardPreview() {
    AppTheme {
        DefaultMoviesCard(
            movie = MovieModel(
                id = 1,
                overview = "This is a movie overview",
                posterPath = "",
                releaseDate = "2022-01-01",
                originalTitle = "The Movie Title",
                voteAverage = "7.5",
                voteCount = "120",
                isFavorite = false,
                backDropPath = ""
            ),
            onClick = {},
        )
    }
}