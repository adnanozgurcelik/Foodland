package com.adnanozgurcelik.foodland.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adnanozgurcelik.foodland.presentation.detail.view.DetailScreen
import com.adnanozgurcelik.foodland.presentation.favorite.view.FavoriteScreen
import com.adnanozgurcelik.foodland.presentation.home.view.HomeScreen
import com.adnanozgurcelik.foodland.presentation.main_composables.AppBottomBar
import com.adnanozgurcelik.foodland.presentation.main_composables.AppTopBar
import com.adnanozgurcelik.foodland.presentation.search.view.SearchScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            AppTopBar(navController = navController)
        },
        bottomBar = {
            AppBottomBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppScreens.Home.name) {
                HomeScreen(navController = navController)
            }
            composable(AppScreens.Search.name) {
                SearchScreen(navController = navController)
            }
            composable(AppScreens.Favorite.name) {
                FavoriteScreen(navController = navController)
            }
            composable(AppScreens.Detail.name + "/{id}") {
                DetailScreen(navController = navController)
            }
        }
    }
}