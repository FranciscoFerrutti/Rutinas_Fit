package ar.edu.itba.rutinas_fit.ui.routine

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.rutinas_fit.data.DataSourceException
import ar.edu.itba.rutinas_fit.data.model.Error
import ar.edu.itba.rutinas_fit.data.model.Routine
import ar.edu.itba.rutinas_fit.data.repository.RoutineRepository
import ar.edu.itba.rutinas_fit.data.repository.UserRepository
import ar.edu.itba.rutinas_fit.ui.user.UserUiState
import ar.edu.itba.rutinas_fit.util.SessionManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RoutineViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val routineRepository: RoutineRepository
) : ViewModel()  {
    var uiState by mutableStateOf(UserUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set
    var routineState by mutableStateOf(RoutineUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
    fun getCurrentUserRoutines() = runOnViewModelScope(
        { userRepository.getCurrentUserRoutines(routineState.routines == null) },
        { state, response -> state.copy(routines = response) }
    )
    fun getRoutines() = runOnViewModelScope(
        { routineRepository.getRoutines(true) },
        { state, response -> state.copy(routines = response) }
    )
    fun getRoutine(routineId: Int) = runOnViewModelScope(
        { routineRepository.getRoutine(routineId) },
        { state, response -> state.copy(currentRoutine = response) }
    )

    fun addOrModifyRoutine(routine: Routine) = runOnViewModelScope(
        {
            if (routine.id == null)
                routineRepository.addRoutine(routine)
            else
                routineRepository.modifyRoutine(routine)
        },
        { state, response -> state.copy(
            currentRoutine = response,
            routines = null) }
    )

    fun deleteRoutine(routineId: Int) = runOnViewModelScope(
        { routineRepository.deleteRoutine(routineId) },
        { state, _ -> state.copy(
            currentRoutine = null,
            routines = null) }
    )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (RoutineUiState, R) -> RoutineUiState
    ): Job = viewModelScope.launch {
        routineState = routineState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            routineState = updateState(routineState, response).copy(isFetching = false)
        }.onFailure { e ->
            routineState = routineState.copy(isFetching = false, error = handleError(e))
        }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "", e.details)
        } else {
            Error(null, e.message ?: "", null)
        }
    }
}