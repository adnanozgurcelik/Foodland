package com.adnanozgurcelik.foodland.presentation.favorite.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.adnanozgurcelik.foodland.data.local.FoodEntity
import com.adnanozgurcelik.foodland.domain.model.info.FoodInfo

@Composable
fun FavoriteColumn(
    foodEntity: FoodEntity,
    onClick: () -> Unit
) {
    var isClicked by remember { mutableStateOf(false) }

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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
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
                    painter = rememberImagePainter(data = foodEntity.image),
                    contentDescription = foodEntity.title
                )
            }
            Text(text = foodEntity.title ?: "")
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
        ) {
            Surface(
                onClick = {
                    isClicked = !isClicked
                          },
                shape = CircleShape
            ) {
                if (isClicked) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite Button",
                        tint = Color.Red
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite Button"
                    )
                }
            }
        }
    }
}




