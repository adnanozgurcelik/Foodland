package com.adnanozgurcelik.foodland.presentation.detail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.adnanozgurcelik.foodland.R
import com.adnanozgurcelik.foodland.presentation.detail.DetailViewModel
import com.adnanozgurcelik.foodland.presentation.detail.view.composable.CustomInfo
import com.adnanozgurcelik.foodland.presentation.detail.view.composable.FoodTag
import com.adnanozgurcelik.foodland.presentation.main_composables.CustomText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    var state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        state.dataFromApi?.let { foodInfo ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                CustomText(
                    content = foodInfo.title ?: "",
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(20.dp))
                Box {
                    Image(
                        painter = rememberImagePainter(data = foodInfo.image),
                        contentDescription = foodInfo.title
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomInfo(
                        image = R.drawable.servings,
                        description = "servings",
                        content = foodInfo.servings.toString()
                    )
                    CustomInfo(
                        image = R.drawable.chronometer,
                        description = "chronometer",
                        content = foodInfo.readyInMinutes.toString()
                    )
                    CustomInfo(
                        image = R.drawable.score,
                        description = "score",
                        content = foodInfo.score!!.toInt().toString()
                    )
                    CustomInfo(
                        image = R.drawable.health,
                        description = "health",
                        content = foodInfo.healthScore.toString()
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 20.dp,
                                end = 20.dp
                            )
                    ) {
                        foodInfo.dishTypes!!.forEach { tag ->
                            FoodTag(tag = tag)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp
                        ),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Dairy Free: ${foodInfo.dairyFree.toString().uppercase()}",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    Text(
                        text = "Gluten Free: ${foodInfo.glutenFree.toString().uppercase()}",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp
                        ),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Vegan: ${foodInfo.vegan.toString().uppercase()}",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    Text(
                        text = "Vegetarian: ${foodInfo.vegetarian.toString().uppercase()}",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 20.dp,
                            end = 20.dp
                        ),
                    shape = RoundedCornerShape(15.dp),
                    tonalElevation = 5.dp
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = 20.dp,
                                end = 20.dp
                            )
                    ) {
                        item {
                            CustomText(
                                content = "Ingredients",
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        items(foodInfo.extendedIngredients!!) { ingredient ->
                            Row {
                                Text(
                                    text = ingredient.measures?.metric?.amount?.toInt().toString(),
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = ingredient.measures?.metric?.unitShort ?: "",
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = ingredient.name ?: "",
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }
                        item {
                            foodInfo.instructions?.let { inst ->
                                Spacer(modifier = Modifier.height(20.dp))
                                CustomText(
                                    content = "Instructions",
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(text = inst)
                            }
                        }
                    }
                }
            }
        }
    }
}