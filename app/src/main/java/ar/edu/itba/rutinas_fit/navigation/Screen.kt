package ar.edu.itba.rutinas_fit.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.itba.rutinas_fit.ExerciseScreen
import ar.edu.itba.rutinas_fit.HomePageScreen
import ar.edu.itba.rutinas_fit.LoginRegisterScreen
import ar.edu.itba.rutinas_fit.RestScreen
import ar.edu.itba.rutinas_fit.RoutineScreen

sealed class Screen(val title: String, val icon: ImageVector, val route: String){
    object Home: Screen("Home", Icons.Filled.Home, "home")
    object Search: Screen("Search", Icons.Filled.Search, "search")
    object Profile: Screen("Profile", Icons.Filled.AccountCircle, "profile")

    object Favorite: Screen("Favorite", Icons.Filled.AccountCircle, "favorite")

    object Routine: Screen("Routine", Icons.Filled.Favorite, "routine")
    object Exercise: Screen("Exercise", Icons.Filled.Favorite, "exercise")
    object Rest: Screen("Rest", Icons.Filled.Favorite, "rest")
    object Login: Screen("Login", Icons.Filled.Favorite, "login")

}
