package com.adnanozgurcelik.foodland.presentation.main_composables

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adnanozgurcelik.foodland.presentation.navigation.AppScreens

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val items = listOf(
        BottomNavItem(
            title = AppScreens.Home.name,
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home
        ),
        BottomNavItem(
            title = AppScreens.Search.name,
            unselectedIcon = Icons.Outlined.Search,
            selectedIcon = Icons.Filled.Search
        ),
        BottomNavItem(
            title = AppScreens.Favorite.name,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            selectedIcon = Icons.Filled.Favorite
        )
    )
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    NavigationBar(
        modifier = Modifier
            .padding(
            bottom = 30.dp,
            start = 20.dp,
            end = 20.dp
        )
            .clip(
                RoundedCornerShape(30.dp)
            ),
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, bottomNavItem ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(bottomNavItem.title)
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItem) bottomNavItem.selectedIcon
                        else bottomNavItem.unselectedIcon,
                        contentDescription = bottomNavItem.title
                    )
                },
                modifier = Modifier.offset(y = 10.dp)
            )
        }
    }
}