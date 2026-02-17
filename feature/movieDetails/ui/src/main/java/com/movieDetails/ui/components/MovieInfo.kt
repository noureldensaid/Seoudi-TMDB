package com.movieDetails.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nour.core.ui.components.text.DefaultText
import com.nour.core.ui.theme.AppTheme

@Composable
fun MovieInfo(
    title: String,
    tagline: String?,
    releaseDate: String,
    language: String?,
    isAdult: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        DefaultText(
            text = title,
            maxLines = 3,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        if (!tagline.isNullOrBlank()) {
            Spacer(Modifier.height(6.dp))
            DefaultText(
                text = "“$tagline”",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
            )
        }

        Spacer(Modifier.height(10.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 3
        ) {
            if (releaseDate.isNotBlank()) Pill(text = releaseDate)
            if (!language.isNullOrBlank()) Pill(text = language.uppercase())
            if (isAdult) Pill(text = "18+", emphasized = true)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieInfoPreview() {
    AppTheme {
        MovieInfo(
            title = "Sample Movie Title",
            tagline = "This is a sample tagline.",
            releaseDate = "2023-10-27",
            language = "EN",
            isAdult = false
        )
    }
}