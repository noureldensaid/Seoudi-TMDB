package com.movieDetails.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AssistChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nour.core.ui.components.text.DefaultText

@Composable
fun ChipsSection(
    title: String,
    icon: ImageVector,
    values: List<String>
) {
    if (values.isEmpty()) return

    Column {
        SectionHeader(title, icon)

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 3
        ) {
            values.forEach { text ->
                AssistChip(onClick = {}, label = {
                    DefaultText(
                        text = text,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                    )
                })
            }
        }
    }
}

@Preview
@Composable
private fun ChipsSectionPreview() {
    ChipsSection(
        title = "Genres",
        icon = ImageVector.vectorResource(id = com.nour.core.ui.R.drawable.ic_image_placeholder),
        values = listOf("Action")
    )
}