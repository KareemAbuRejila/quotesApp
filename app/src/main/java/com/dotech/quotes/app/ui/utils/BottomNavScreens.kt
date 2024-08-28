package com.dotech.quotes.app.ui.utils

sealed class BottomNavScreens(val route: String) {
    object Home : BottomNavScreens("home")
    object Quotes : BottomNavScreens("quotes")
    object About : BottomNavScreens("about")
}