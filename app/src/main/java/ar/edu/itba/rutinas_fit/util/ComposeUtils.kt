package ar.edu.itba.rutinas_fit.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import ar.edu.itba.rutinas_fit.MyApplication

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as MyApplication)
    val sessionManager = application.sessionManager
    val userRepository = application.userRepository
    val sportRepository = application.sportRepository
    val routineRepository = application.routineRepository
    val cycleRepository = application.cycleRepository
    val cycleExerciseRepository = application.cycleExerciseRepository
    return ViewModelFactory(sessionManager, userRepository, sportRepository, routineRepository,cycleRepository,cycleExerciseRepository, LocalSavedStateRegistryOwner.current, defaultArgs)
}