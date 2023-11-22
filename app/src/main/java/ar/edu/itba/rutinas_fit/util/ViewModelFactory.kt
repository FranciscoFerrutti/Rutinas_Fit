package ar.edu.itba.rutinas_fit.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ar.edu.itba.rutinas_fit.classes.MainViewModel
import ar.edu.itba.rutinas_fit.data.repository.CycleExerciseRepository
import ar.edu.itba.rutinas_fit.data.repository.CycleRepository
import ar.edu.itba.rutinas_fit.data.repository.RoutineRepository
import ar.edu.itba.rutinas_fit.data.repository.SportRepository
import ar.edu.itba.rutinas_fit.data.repository.UserRepository

class ViewModelFactory constructor(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val sportRepository: SportRepository,
    private val routineRepository: RoutineRepository,
    private val cycleRepository: CycleRepository,
    private val cycleExerciseRepository: CycleExerciseRepository,
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
                MainViewModel(sessionManager, userRepository, routineRepository, sportRepository, cycleRepository, cycleExerciseRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}