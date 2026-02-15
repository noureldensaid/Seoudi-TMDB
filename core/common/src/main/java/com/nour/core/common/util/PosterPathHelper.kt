package com.nour.core.common.util

private const val TMDB_IMAGE_BASE_ORIGINAL = "https://image.tmdb.org/t/p/original"

fun tmdbPosterUrl(
    path: String?,
): String? {
    if (path.isNullOrBlank()) return null
    return TMDB_IMAGE_BASE_ORIGINAL + path
}