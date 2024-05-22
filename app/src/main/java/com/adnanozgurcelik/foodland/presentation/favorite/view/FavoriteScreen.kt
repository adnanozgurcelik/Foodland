package com.adnanozgurcelik.foodland.presentation.favorite.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adnanozgurcelik.foodland.presentation.favorite.FavoriteViewModel
import com.adnanozgurcelik.foodland.presentation.favorite.view.composables.FavoriteColumn
import com.adnanozgurcelik.foodland.presentation.main_composables.CustomText
import com.adnanozgurcelik.foodland.presentation.navigation.AppScreens

@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    var state = viewModel.state
    
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        CustomText(
            content = "Favorite",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(
                    start = 20.dp
                )
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.data!!) { favorite -> 
                FavoriteColumn(foodEntity = favorite) {
                    navController.navigate(AppScreens.Detail.name)
                }
            }
        }
    }
}