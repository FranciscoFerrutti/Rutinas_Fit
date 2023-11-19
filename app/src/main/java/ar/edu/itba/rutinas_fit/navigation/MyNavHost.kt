package ar.edu.itba.rutinas_fit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.itba.rutinas_fit.ExerciseScreen
import ar.edu.itba.rutinas_fit.HomePageScreen
import ar.edu.itba.rutinas_fit.LoginRegisterScreen
import ar.edu.itba.rutinas_fit.RestScreen
import ar.edu.itba.rutinas_fit.RoutineScreen
import ar.edu.itba.rutinas_fit.UserProfileScreen

@Composable
fun MyNavHost(
    navController: NavHostController,
    startDestination: String = "profile"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            HomePageScreen(navController)
        }
        composable(Screen.Search.route) {
            //SearchScreen()
        }
        composable(Screen.Profile.route) {
            UserProfileScreen(navController)
        }
        composable(Screen.Favorite.route) {
            //ListScreen()
        }
        composable(Screen.Routine.route) {
            RoutineScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginRegisterScreen(navController)
        }
        composable(Screen.Exercise.route) {
            ExerciseScreen(navController)
        }
        composable(Screen.Rest.route) {
            RestScreen()
        }
    }
}