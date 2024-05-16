package com.adnanozgurcelik.foodland.presentation.home.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adnanozgurcelik.foodland.R

@Composable
fun ExploreArea(
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp
            ),
        shape = RoundedCornerShape(15.dp),
        color = Color(0xFF106B44),
        shadowElevation = 8.dp
    ) {
        Row {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Cook the best",
                    modifier = Modifier
                        .padding(
                            start = 40.dp,
                            top = 20.dp
                        ),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.background
                )
                Text(
                    text = "recipe at home",
                    modifier = Modifier
                        .padding(
                            start = 40.dp,
                            bottom = 10.dp
                        ),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.background
                )
                Button(
                    onClick = {
                        onClick.invoke()
                    },
                    modifier = Modifier
                        .padding(start = 40.dp),
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        disabledContentColor = MaterialTheme.colorScheme.onBackground,
                        disabledContainerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text(
                        text = "Explore"
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chef),
                    contentDescription = "Chef Image",
                    modifier = Modifier
                        .height(160.dp)
                        .width(160.dp)
                )
            }
        }
    }
}



