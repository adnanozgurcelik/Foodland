package com.adnanozgurcelik.foodland.presentation.detail.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomInfo(
    image: Int,
    description: String,
    content: String
) {
    Box {
        Row {
            Image(
                painter = painterResource(id = image),
                contentDescription = description,
                modifier = Modifier
                    .size(20.dp)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = content,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}