package com.movieDetails.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MoneyCards(
    budget: String,
    revenue: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        InfoCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.AttachMoney,
            title = "Revenue",
            value = revenue
        )
        InfoCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.AttachMoney,
            title = "Budget",
            value = budget
        )
    }
}

@Preview
@Composable
private fun MoneyCardsPreview() {
    MoneyCards(
        budget = "$ 10000",
        revenue = "$ 100000000"
    )
}
