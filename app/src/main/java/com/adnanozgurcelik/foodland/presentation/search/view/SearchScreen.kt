package com.adnanozgurcelik.foodland.presentation.search.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adnanozgurcelik.foodland.presentation.main_composables.CustomText
import com.adnanozgurcelik.foodland.presentation.navigation.AppScreens
import com.adnanozgurcelik.foodland.presentation.search.SearchEvent
import com.adnanozgurcelik.foodland.presentation.search.SearchViewModel
import com.adnanozgurcelik.foodland.presentation.search.view.composable.CustomSearchBar
import com.adnanozgurcelik.foodland.presentation.search.view.composable.FoodColumn

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    var state = viewModel.state
    var searchQuery by remember { mutableStateOf("") }
    var hint by remember { mutableStateOf("Search...") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        CustomText(
            content = "Search",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp
                )
                .clip(
                    shape = RoundedCornerShape(15.dp)
                )
                .background(MaterialTheme.colorScheme.background)
                .height(40.dp),
            value = searchQuery,
            hint = hint,
            onValueChanged = { search ->
                searchQuery = search
                viewModel.onEvent(SearchEvent.SearchQueryChange(searchQuery))
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn {
                items(state.data!!.size) { index ->
                    val foods = state.data!![index]
                    FoodColumn(search = foods) {
                        navController.navigate(AppScreens.Detail.name + "/${foods.id}")
                    }
                }
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
        if (state.error!!.isNotBlank()) Text(text = state.error!!, color = MaterialTheme.colorScheme.error)
    }
}