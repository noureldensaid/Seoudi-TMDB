package com.nour.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nour.core.common.util.tmdbPosterUrl

@Composable
fun PosterImage(
    posterPath: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = tmdbPosterUrl(posterPath),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier.clip(RoundedCornerShape(8.dp))
    )
}