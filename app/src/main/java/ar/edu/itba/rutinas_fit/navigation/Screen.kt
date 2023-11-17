package ar.edu.itba.rutinas_fit.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val icon: ImageVector, val route: String){
    object Home: Screen("Home", Icons.Filled.Home, "home")
    object Search: Screen("Search", Icons.Filled.Search, "search")
    object Profile: Screen("Profile", Icons.Filled.AccountCircle, "profile")
    object Favorite: Screen("Favorite", Icons.Filled.Favorite, "favorite")
}
