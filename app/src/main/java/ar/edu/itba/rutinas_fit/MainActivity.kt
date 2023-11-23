package ar.edu.itba.rutinas_fit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.itba.rutinas_fit.ui.theme.Rutinas_FitTheme
//import androidx.compose.foundation.layout.Side
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight

//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.rutinas_fit.classes.MainViewModel
import ar.edu.itba.rutinas_fit.navigation.MyNavHost
import ar.edu.itba.rutinas_fit.navigation.Screen

import ar.edu.itba.rutinas_fit.util.getViewModelFactory
@Composable
fun MyNavHost2(
    startDestination: String = "home",
    navController: NavHostController
) {
    NavHost(
        navController = navController ,
        startDestination = startDestination
    ) {
        composable(Screen.Routine.route + "/{routineId}") {backStackEntry ->
            val routineId = backStackEntry.arguments?.getString("routineId")
            RoutineScreen(navController, routineId.orEmpty())
        }
        composable(Screen.Home.route) {
            HomePageScreen(navController)
        }


    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Rutinas_FitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(10, 10, 10)
                ) {

                    val context = LocalContext.current
                    val navController = rememberNavController()
                    val mainViewModel = viewModel<MainViewModel>(factory = getViewModelFactory())

                    // Check if the activity was started by a deep link
                    if (Intent.ACTION_VIEW == intent.action) {
                        val uri = intent.data
                        if (uri != null) {
                            // Extract data from the URI and navigate to the appropriate screen
                            MyNavHost(navController = navController, startDestination = Screen.Home.route)
                            handleDeepLink(uri, navController)
                        }
                    } else {
                        // If not a deep link, check the authentication state
                        if (!mainViewModel.uiState.isAuthenticated) {
                            MyNavHost(navController = navController,startDestination = Screen.Login.route)
                        } else {
                            MyNavHost(navController = navController,startDestination = Screen.Home.route)
                        }
                    }
                }
            }
        }
    }

    private fun handleDeepLink(uri: Uri, navController: NavHostController) {
        val pathSegments = uri.pathSegments
        val lastSegment = pathSegments.lastOrNull()

        // Check if the last segment is not null or blank before navigating
        if (!lastSegment.isNullOrBlank()) {
            navigateToRoutine2(navController, lastSegment)
        } else {
            // Handle the case when the last segment is not provided or blank
            // You might want to navigate to a default screen or show an error message
            Log.e("DeepLink", "Invalid routineId provided in the deep link")
        }
    }
}
fun navigateToRoutine2(navController : NavController, routineId : String){
    navController.navigate("${Screen.Routine.route}/$routineId")
}
fun navigateToRoutine(navController : NavController, routineId : String){
    navController.navigate("${Screen.Routine.route}/$routineId")
}
fun navigateToFavorite(navController : NavController){
    navController.navigate(Screen.Favorite.route)
}

fun navigateToSettings(navController : NavController){
    navController.navigate(Screen.Settings.route)
}

fun navigateToHome(navController : NavController){
    navController.navigate(Screen.Home.route)
}

fun navigateToExercise(navController : NavController,routineId : String){
    navController.navigate("${Screen.Exercise.route}/$routineId")
}

fun navigateToRest(navController : NavController){
    navController.navigate(Screen.Rest.route)
}
fun navigateToSearch(navController : NavController){
    navController.navigate(Screen.Search.route)
}

fun navigateToProfile(navController: NavController) {
    navController.navigate(Screen.Profile.route)
}
fun navigateToLogin(navController: NavController) {
    navController.navigate(Screen.Login.route)
}