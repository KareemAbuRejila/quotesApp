package com.dotech.quotes.app.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun BottomNavigationBar(navController: NavController,currentDestination: NavDestination?){
    val items = listOf(
        BottomNavScreens.Home,BottomNavScreens.Quotes,BottomNavScreens.About
    )

    NavigationBar {
        items.forEach { screen->
            NavigationBarItem(
                selected =currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route){
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                label = {Text(text = screen.route.capitalize(), color = Color.White)},
                icon = {
                    when(screen) {
                        BottomNavScreens.Home -> Icon (
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            tint = Color.White
                        )
                        BottomNavScreens.Quotes -> Icon (
                            imageVector = Icons.Default.FormatQuote,
                            contentDescription = null,
                            tint = Color.White
                        )
                        BottomNavScreens.About -> Icon (
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        }
    }
}