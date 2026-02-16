package com.nour.core.ui.components.asyncImage

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

private const val TMDB_IMAGE_BASE_ORIGINAL = "https://image.tmdb.org/t/p/original"

@Composable
fun PosterImage(
    posterPath: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    alpha: Float = 1f
) {
    AsyncImage(
        model = tmdbPosterUrl(posterPath),
        contentDescription = null,
        contentScale =  contentScale,
        alpha = alpha,
        modifier = modifier.clip(RoundedCornerShape(8.dp))
    )
}

fun tmdbPosterUrl(
    path: String?,
): String? {
    if (path.isNullOrBlank()) return null
    return TMDB_IMAGE_BASE_ORIGINAL + path
}