package com.adnanozgurcelik.foodland.presentation.home.view.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CuisineRow(
    content: String,
    onClick: () -> Unit = {}
) {
    Surface(
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier
            .padding(start = 20.dp),
        color = MaterialTheme.colorScheme.background,
        tonalElevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 8.dp
    ) {
        Text(
            text = content,
            modifier = Modifier
                .padding(7.dp)
        )
    }
}