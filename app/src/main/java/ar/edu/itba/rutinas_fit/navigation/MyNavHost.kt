package ar.edu.itba.rutinas_fit.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.itba.rutinas_fit.ExerciseScreen
import ar.edu.itba.rutinas_fit.HomePageScreen
import ar.edu.itba.rutinas_fit.LoginRegisterScreen
import ar.edu.itba.rutinas_fit.R
import ar.edu.itba.rutinas_fit.RestScreen
import ar.edu.itba.rutinas_fit.RoutineScreen
import ar.edu.itba.rutinas_fit.UserProfileScreen

@Composable
fun MyNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            HomePageScreen(navController)
        }/*
        composable(Screen.Search.route) {
            val imageResource = R.drawable.gymimg
            val imageList = listOf(
                ImageData(imageResource, "Bajar de Peso", "Quemar Calorias y ponerme en forma rapido"),
                ImageData(imageResource, "Ganar musculo", "Subir la masa muscular y ponerme en forma rapido"),
                // Add more images as needed
            )

            GoalSelectionScreen(navController, imageList)
            //SearchScreen()
        }
        composable(Screen.Profile.route) {
            val exerciseName = "Salto Tijera"
            val difficulty = "Intermedio"
            val description = "Los saltos de tijera consisten en saltar con los pies separados a la altura de los hombros mientras llevas los brazos por encima de la cabeza, y luego volver a saltar con los pies juntos mientras bajas los brazos a los lados. Leer Más..."
            val steps = listOf(
                ExerciseStep("Separa tus brazos", "Para que los gestos se sientan más relajados, estire los brazos al iniciar este movimiento. Sin doblar las manos."),
                ExerciseStep("En puntas de pie", "La base de este movimiento es el salto. Ahora bien, lo que hay que considerar es que hay que utilizar las puntas de los pies."),
                ExerciseStep("Ajustar el movimiento del pie", "El Salto Tijera no es un salto cualquiera. Hay que prestar mucha atención a los movimientos de las piernas."),
                ExerciseStep("Aplaudir", "Verás, sin darte cuenta, que las palmas te ayudan a mantener el ritmo mientras haces el Salto Tijera.")
            )

            ExerciseDetailsScreen(exerciseName, difficulty, description, steps)
            //ProfileScreen()
        }
        composable(Screen.Favorite.route) {
            //ListScreen()
        }*/
        composable(Screen.Routine.route) {
            RoutineScreen(navController)
        }
        composable(Screen.Profile.route){
            UserProfileScreen(navController)
        }
        composable(Screen.Search.route){
            LoginRegisterScreen(navController = navController)
        }
        composable(Screen.Login.route) {
            LoginRegisterScreen(navController = navController)
        }/*
        composable(Screen.Exercise.route) {
            ExerciseScreen(navController)
        }
        composable(Screen.Rest.route) {
            RestScreen()
        }*/

    }
}



fun navigateToRoutine(navController : NavController){
    navController.navigate(Screen.Routine.route)
}

fun navigateToHome(navController : NavController){
    navController.navigate(Screen.Home.route)
}

fun navigateToExercise(navController : NavController){
    navController.navigate(Screen.Exercise.route)
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
