package ar.edu.itba.rutinas_fit.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ar.edu.itba.rutinas_fit.data.repository.ExerciseRepository
import ar.edu.itba.rutinas_fit.data.repository.RoutineRepository
import ar.edu.itba.rutinas_fit.data.repository.SportRepository
import ar.edu.itba.rutinas_fit.data.repository.UserRepository
import ar.edu.itba.rutinas_fit.ui.exercise.ExerciseViewModel
import ar.edu.itba.rutinas_fit.ui.main.MainViewModel
import ar.edu.itba.rutinas_fit.ui.routine.RoutineViewModel

class ViewModelFactory constructor(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val sportRepository: SportRepository,
    private val routineRepository: RoutineRepository,
    private val exerciseRepository: ExerciseRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(sessionManager, userRepository, sportRepository)
            //isAssignableFrom(ProfileViewModel::class.java) ->
            //   ProfileViewModel(sessionManager, userRepository, ...)
            // ...
            isAssignableFrom(RoutineViewModel::class.java) ->
                RoutineViewModel(sessionManager, userRepository, routineRepository)
            isAssignableFrom(ExerciseViewModel::class.java) ->
                ExerciseViewModel(sessionManager, userRepository, exerciseRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}