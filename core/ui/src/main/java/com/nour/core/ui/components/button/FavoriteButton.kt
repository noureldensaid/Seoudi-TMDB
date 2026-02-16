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
import com.nour.core.ui.theme.golden

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onToggle: () -> Unit
) {
    IconButton(
        onClick = onToggle,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
        )
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
            contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
            tint = if (isFavorite) golden else MaterialTheme.colorScheme.surface
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