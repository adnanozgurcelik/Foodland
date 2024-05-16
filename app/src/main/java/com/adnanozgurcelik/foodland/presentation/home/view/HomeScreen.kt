package com.adnanozgurcelik.foodland.presentation.home.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adnanozgurcelik.foodland.presentation.home.Cuisines
import com.adnanozgurcelik.foodland.presentation.home.HomeViewModel
import com.adnanozgurcelik.foodland.presentation.home.view.composable.CuisineRow
import com.adnanozgurcelik.foodland.presentation.home.view.composable.CustomText
import com.adnanozgurcelik.foodland.presentation.home.view.composable.ExploreArea
import com.adnanozgurcelik.foodland.presentation.home.view.composable.RecommendedFoods
import com.adnanozgurcelik.foodland.presentation.navigation.AppScreens

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        CustomText(
            content = "What would you like",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(
                    start = 20.dp
                )
        )
        CustomText(
            content = "to cook today?",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(
                    start = 20.dp
                )
        )
        Spacer(modifier = Modifier.height(20.dp))
        ExploreArea {
            navController.navigate(AppScreens.Search.name)
        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomText(
            content = "Cuisines",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(
                    start = 20.dp
                )
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow {
            var cuisines = Cuisines().cuisines
            items(cuisines.size) { item ->
                CuisineRow(
                    content = cuisines[item],
                    onClick = {
                        navController.navigate(AppScreens.Search.name)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomText(
            content = "Recommended",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(
                    start = 20.dp
                )
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow {
            items(state.data!!.size) { index ->
                val foods = state.data!![index]
                RecommendedFoods(
                    search = foods,
                    onClick = {
                        navController.navigate(route = AppScreens.Detail.name + "/${foods.id}")
                    }
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!state.isLoading) CircularProgressIndicator()
        if (state.error.isNotBlank()) Text(text = state.error, color = MaterialTheme.colorScheme.error)
    }
}