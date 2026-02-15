package com.nour.core.ui.components.chip

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RatingChip(
    rating: Double?,
    voteCount: Int?,
    leadingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val text = buildString {
        append(rating ?: 0.0)
        if ((voteCount ?: 0) > 0) append("  (${voteCount})")
    }
    AssistChip(
        leadingIcon = leadingIcon,
        onClick = { },
        label = {
            Text(text, style = MaterialTheme.typography.labelLarge)
        },
        modifier = modifier,
        border = null,
        colors = AssistChipDefaults.assistChipColors()
    )
}