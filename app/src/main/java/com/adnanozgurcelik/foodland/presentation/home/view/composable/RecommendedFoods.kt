package com.adnanozgurcelik.foodland.presentation.home.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.adnanozgurcelik.foodland.domain.model.search.Search

@Composable
fun RecommendedFoods(
    search: Search,
    onClick: () -> Unit = {}
) {
    Surface(
        onClick = { onClick.invoke() },
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp
            )
            .height(200.dp)
            .width(150.dp),
        color = MaterialTheme.colorScheme.background,
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .height(100.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    )
            ) {
                Image(
                    painter = rememberImagePainter(data = search.image),
                    contentDescription = search.title
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Top
                ) {
                    var isClicked by remember { mutableStateOf(false) }
                    Surface(
                        onClick = {
                            isClicked = !isClicked
                        },
                        shape = CircleShape
                    ) {
                        if (isClicked) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Favorite",
                                tint = Color.Red
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorite"
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = search.title,
                modifier = Modifier
                    .padding(1.dp)
            )
        }
    }
}


