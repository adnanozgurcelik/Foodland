package com.adnanozgurcelik.foodland.presentation.search.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.adnanozgurcelik.foodland.domain.model.search.Search

@Composable
fun FoodColumn(
    search: Search,
    onClick: () -> Unit
) {
    Surface(
        onClick = { onClick.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 10.dp
            )
            .height(100.dp),
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .height(80.dp)
                    .clip(
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Image(
                    painter = rememberImagePainter(data = search.image),
                    contentDescription = search.title
                )
            }
            Text(
                text = search.title
            )
        }
    }
}