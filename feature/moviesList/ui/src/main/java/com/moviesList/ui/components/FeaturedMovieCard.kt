package com.moviesList.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviesList.domain.model.MovieModel
import com.nour.core.ui.components.asyncImage.PosterImage
import com.nour.core.ui.components.text.DefaultText
import com.nour.core.ui.extensions.skipInteraction

@Composable
fun FeaturedMovieCard(
    movie: MovieModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(170.dp),
        onClick = onClick,
        interactionSource = skipInteraction(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box(Modifier.fillMaxSize()) {

            PosterImage(
                posterPath = movie.backDropPath,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background
                            ),
                            startY = 0f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(14.dp)
                    .fillMaxWidth()
            ) {
                DefaultText(
                    text = movie.originalTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                )

                Spacer(Modifier.height(6.dp))

                Row(
                    modifier= Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DefaultText(
                        text = "Release • ${movie.releaseDate}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                    RateCard(
                        voteAverage = movie.voteAverage,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FeaturedMovieCardPreview() {
    FeaturedMovieCard(
        movie = MovieModel(
            id = 7400,
            overview = "discere",
            posterPath = "convallis",
            backDropPath = "rhoncus",
            releaseDate = "mi",
            originalTitle = "suspendisse",
            voteAverage = "maiorum",
            voteCount = "vidisse",
            isFavorite = false
        ),
        onClick = {}
    )
}
