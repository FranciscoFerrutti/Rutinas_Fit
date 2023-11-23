package ar.edu.itba.rutinas_fit.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.itba.rutinas_fit.ExerciseScreen
import ar.edu.itba.rutinas_fit.FavoriteScreen
import ar.edu.itba.rutinas_fit.HomePageScreen
import ar.edu.itba.rutinas_fit.LoginRegisterScreen
import ar.edu.itba.rutinas_fit.RestScreen
import ar.edu.itba.rutinas_fit.ReviewScreen
import ar.edu.itba.rutinas_fit.RoutineScreen
import ar.edu.itba.rutinas_fit.SearchScreen
import ar.edu.itba.rutinas_fit.SettingsScreen
import ar.edu.itba.rutinas_fit.UserProfileScreen
import ar.edu.itba.rutinas_fit.data.model.Routine


@Composable
fun MyNavHost(
    startDestination: String = "home",
    navController: NavHostController
) {
    NavHost(
        navController = navController ,
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
        }*/
        composable(Screen.Favorite.route) {
            FavoriteScreen(navController)
        }
        composable(Screen.Routine.route + "/{routineId}") {backStackEntry ->
            val routineId = backStackEntry.arguments?.getString("routineId")
            RoutineScreen(navController, routineId.orEmpty())
        }
        composable(Screen.Login.route) {
            LoginRegisterScreen(navController)
        }

        composable(Screen.Exercise.route + "/{routineId}") {backStackEntry ->
            val routineId = backStackEntry.arguments?.getString("routineId")
            ExerciseScreen(navController, routineId.orEmpty())
        }
        composable(Screen.Rest.route) {
            RestScreen()
        }
        composable(Screen.Search.route) {
            SearchScreen(navController)
        }
        composable(Screen.Profile.route) {
            UserProfileScreen(navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }
        composable(Screen.Review.route){
            ReviewScreen(navController)
        }

    }
}



//fun navigateToRoutine(navController : NavController, routineId : String){
//    navController.navigate("${Screen.Routine.route}/$routineId")
//}
//fun navigateToFavorite(navController : NavController){
//    navController.navigate(Screen.Favorite.route)
//}
//
//fun navigateToSettings(navController : NavController){
//    navController.navigate(Screen.Settings.route)
//}
//
//fun navigateToHome(navController : NavController){
//    navController.navigate(Screen.Home.route)
//}
//
//fun navigateToExercise(navController : NavController,routineId : String){
//    navController.navigate("${Screen.Exercise.route}/$routineId")
//}
//
//fun navigateToRest(navController : NavController){
//    navController.navigate(Screen.Rest.route)
//}
//fun navigateToSearch(navController : NavController){
//    navController.navigate(Screen.Search.route)
//}
//
//fun navigateToProfile(navController: NavController) {
//    navController.navigate(Screen.Profile.route)
//}
//fun navigateToLogin(navController: NavController) {
//    navController.navigate(Screen.Login.route)
//}

fun navigateToReview(navController: NavController){
    navController.navigate(Screen.Review.route)
}
