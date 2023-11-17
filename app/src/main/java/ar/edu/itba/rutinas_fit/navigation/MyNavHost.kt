package ar.edu.itba.rutinas_fit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            //HomeScreen() /* TODO: */
        }
        composable(Screen.Search.route) {
            //SearchScreen()
        }
        composable(Screen.Profile.route) {
            //ProfileScreen()
        }
        composable(Screen.Favorite.route) {
            //ListScreen()
        }
    }
}