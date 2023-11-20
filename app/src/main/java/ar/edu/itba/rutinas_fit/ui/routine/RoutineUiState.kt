package ar.edu.itba.rutinas_fit.ui.routine

import androidx.compose.runtime.mutableStateOf
import ar.edu.itba.rutinas_fit.data.model.Error
import ar.edu.itba.rutinas_fit.data.model.Routine
import ar.edu.itba.rutinas_fit.data.model.User
import ar.edu.itba.rutinas_fit.ui.user.UserUiState

data class RoutineUiState(
    val isAuthenticated: Boolean = mutableStateOf(UserUiState().isAuthenticated).value,
    val isFetching: Boolean = mutableStateOf(UserUiState().isFetching).value,
    val currentUser: User? = null,
    val routines: List<Routine>? = null,
    val currentRoutine: Routine? = null,
    val error: Error? = null
)

val RoutineUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val RoutineUiState.canGetAllRoutines: Boolean get() = isAuthenticated
val RoutineUiState.canGetCurrentRoutine: Boolean get() = isAuthenticated && currentRoutine != null
val RoutineUiState.canAddRoutine: Boolean get() = isAuthenticated && currentRoutine == null
val RoutineUiState.canModifyRoutine: Boolean get() = isAuthenticated && currentRoutine != null
val RoutineUiState.canDeleteRoutine: Boolean get() = canModifyRoutine
