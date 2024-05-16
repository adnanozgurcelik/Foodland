package com.adnanozgurcelik.foodland.presentation.main_composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    content: String,
    color: Color
) {
    Text(
        text = content,
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold,
        color = color
    )
}