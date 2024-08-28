package com.dotech.quotes.app.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dotech.quotes.app.ui.about.AboutScreen
import com.dotech.quotes.app.ui.home.HomeScreen
import com.dotech.quotes.app.ui.quotes.QuotesScreen
import com.dotech.quotes.app.ui.theme.QuotesTheme
import com.dotech.quotes.app.ui.utils.BottomNavScreens
import com.dotech.quotes.app.ui.utils.BottomNavigationBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewmodel: MainViewModel by viewModels()
            Log.i("TAG", "onCreate: ${viewmodel.responseList.value}")
            MainScreen(viewModel = viewmodel)
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel?) {
    QuotesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        currentDestination = currentDestination
                    )
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = BottomNavScreens.About.route,
                    modifier = Modifier.padding(paddingValues = innerPadding)
                ) {
                    composable(BottomNavScreens.Home.route){
                        HomeScreen(navController = navController)
                    }
                    composable(BottomNavScreens.Quotes.route){
                        QuotesScreen(navController = navController)
                    }
                    composable(BottomNavScreens.About.route){
                        AboutScreen(navController = navController)
                    }
                }
            }
        }
    }
}


@Preview()
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreenScreen() {
    QuotesTheme {
        MainScreen(null)
    }
}