package com.nour.core.ui.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nour.core.ui.theme.amber

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onToggle: () -> Unit
) {
    IconButton(
        onClick = onToggle,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
            contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
            tint = if (isFavorite) amber else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
private fun FavoriteButtonPreview() {
    Column {
        FavoriteButton(isFavorite = true, onToggle = {})
        FavoriteButton(isFavorite = false, onToggle = {})
    }
}