package com.movieDetails.ui.components

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nour.core.ui.components.text.DefaultText

@Composable
fun Pill(text: String, emphasized: Boolean = false) {
    val colors = if (emphasized) {
        AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            labelColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    } else {
        AssistChipDefaults.assistChipColors()
    }

    AssistChip(
        onClick = {},
        label = {
            DefaultText(
                text = text, maxLines = 1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )
        },
        colors = colors
    )
}
