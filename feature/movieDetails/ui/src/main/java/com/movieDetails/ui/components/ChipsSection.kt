package com.movieDetails.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AssistChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nour.core.ui.components.text.DefaultText

@Composable
fun ChipsSection(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    values: List<String>
) {
    if (values.isEmpty()) return

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