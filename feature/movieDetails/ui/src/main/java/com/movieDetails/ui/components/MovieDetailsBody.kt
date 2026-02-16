package com.movieDetails.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.TrendingUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movieDetails.domain.model.MovieDetailsModel

@Composable
fun MovieDetailsBody(
    modifier: Modifier = Modifier,
    movie: MovieDetailsModel,
) {
    val bodyScroll = rememberScrollState()

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp),
        tonalElevation = 8.dp,
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp)
                .windowInsetsPadding(WindowInsets.navigationBars)
                .verticalScroll(bodyScroll),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Spacer(Modifier.height(16.dp))

            MovieInfo(
                title = movie.originalTitle.ifBlank { movie.title.orEmpty() },
                tagline = movie.tagline,
                releaseDate = movie.releaseDate,
                language = movie.originalLanguage,
                isAdult = movie.adult == true
            )

            ChipsSection(
                title = "Genres",
                icon = Icons.AutoMirrored.Outlined.TrendingUp,
                values = movie.genres.values.toList()
            )

            StatCardRow(
                runtimeMin = movie.runtime,
                rating = movie.voteAverage,
                votes = movie.voteCount,
                status = movie.status
            )

            ExpandableOverview(
                title = "Overview",
                text = movie.overview
            )

            MoneyCards(
                budget = movie.budget,
                revenue = movie.revenue,
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsBodyPreview() {
    com.nour.core.ui.theme.AppTheme {
        MovieDetailsBody(
            movie = MovieDetailsModel(
                originalTitle = "The Movie Title",
                title = "The Movie Title",
                tagline = "An amazing movie.",
                releaseDate = "2023-10-27",
                originalLanguage = "en",
                adult = false,
                runtime = "120 min",
                voteAverage = "8.5",
                voteCount = "1200",
                status = "Released",
                budget = "$100,000,000",
                revenue = "$500,000,000",
                genres = mapOf(28 to "Action", 12 to "Adventure", 878 to "Science Fiction"),
                overview = "This is a great movie about a hero who saves the world. It is full of action, adventure, and science fiction."
            )
        )
    }
}