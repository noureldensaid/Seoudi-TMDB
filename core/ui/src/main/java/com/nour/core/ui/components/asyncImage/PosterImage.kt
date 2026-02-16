package com.nour.core.ui.components.asyncImage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.valentinilk.shimmer.shimmer

private const val TMDB_IMAGE_BASE_ORIGINAL = "https://image.tmdb.org/t/p/original"

@Composable
fun PosterImage(
    posterPath: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    alpha: Float = 1f
) {
    var isLoading by retain { mutableStateOf(true) }

    val shape = RoundedCornerShape(8.dp)

    Box(modifier = modifier.clip(shape)) {

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .shimmer()
            )
        }
        AsyncImage(
            model = posterPath?.let { tmdbPosterUrl(it) },
            contentDescription = null,
            contentScale = contentScale,
            alpha = alpha,
            onLoading = { isLoading = true },
            onSuccess = { isLoading = false },
            onError = { isLoading = false },
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun tmdbPosterUrl(
    path: String?,
): String? {
    if (path.isNullOrBlank()) return null
    return TMDB_IMAGE_BASE_ORIGINAL + path
}