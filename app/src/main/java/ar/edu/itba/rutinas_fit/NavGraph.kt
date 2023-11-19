package ar.edu.itba.rutinas_fit

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MyNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomePageScreen(navController)
        }
        composable("routine") {
            RoutineScreen(navController)
        }
        composable("login") {
            LoginRegisterScreen(navController)
        }
        composable("exercise") {
            ExerciseScreen(navController)
        }
        composable("rest") {
            RestScreen()
        }
        composable("user") {
            UserProfileScreen(navController)
        }
    }
}