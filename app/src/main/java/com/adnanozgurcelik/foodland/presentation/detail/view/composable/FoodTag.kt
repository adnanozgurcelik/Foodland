package com.adnanozgurcelik.foodland.presentation.detail.view.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FoodTag(
    tag: String
) {
    Box(
        modifier = Modifier
            .padding(
                end = 5.dp,
                bottom = 5.dp
            )
            .border(
                width = 1.dp,
                color = Color(0xFF106B44),
                shape = RoundedCornerShape(100.dp)
            )
    ) {
        Text(
            text = tag,
            color = Color(0xFF106B44),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(5.dp)
        )
    }
}