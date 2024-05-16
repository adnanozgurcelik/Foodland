package com.adnanozgurcelik.foodland.presentation.main_composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.adnanozgurcelik.foodland.presentation.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var showMenu by rememberSaveable { mutableStateOf(false) }

    TopAppBar(
        title = { 
            Text(
                text = "Foodland",
                fontWeight = FontWeight.SemiBold
            )
        },
        actions = {
            IconButton(
                onClick = { showMenu = !showMenu }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu Button"
                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "About")
                    },
                    onClick = {
                        navController.navigate(AppScreens.About.name)
                    }
                )
            }
        }
    )
}