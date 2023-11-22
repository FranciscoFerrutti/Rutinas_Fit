package ar.edu.itba.rutinas_fit.ui

import ar.edu.itba.rutinas_fit.data.model.Error
import ar.edu.itba.rutinas_fit.data.model.Exercise
import ar.edu.itba.rutinas_fit.data.model.Routine
import ar.edu.itba.rutinas_fit.data.model.Sport
import ar.edu.itba.rutinas_fit.data.model.User
import ar.edu.itba.rutinas_fit.ui.user.UserUiState

data class MainUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val error: Error? = null,
    val sports: List<Sport>? = null,
    val currentSport: Sport? = null,
    val routines: List<Routine>? = null,
    val currentRoutine: Routine? = null,
    val exercises: List<Exercise>? = null,
    val currentExercise: Exercise? = null
)

val MainUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val MainUiState.canLogin: Boolean get() = !isAuthenticated
val MainUiState.canLogout: Boolean get() = isAuthenticated
val MainUiState.canGetAllSports: Boolean get() = isAuthenticated
val MainUiState.canGetCurrentSport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canAddSport: Boolean get() = isAuthenticated //&& currentSport == null
val MainUiState.canModifySport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canDeleteSport: Boolean get() = canModifySport
val MainUiState.canGetAllRoutines: Boolean get() = isAuthenticated
val MainUiState.canGetCurrentRoutine: Boolean get() = isAuthenticated && currentRoutine != null
val MainUiState.canAddRoutine: Boolean get() = isAuthenticated && currentRoutine == null
val MainUiState.canModifyRoutine: Boolean get() = isAuthenticated && currentRoutine != null
val MainUiState.canDeleteRoutine: Boolean get() = canModifyRoutine
val MainUiState.canGetAllExercises: Boolean get() = isAuthenticated
val MainUiState.canGetCurrentExercise: Boolean get() = isAuthenticated && currentExercise != null
val MainUiState.canAddExercise: Boolean get() = isAuthenticated && currentExercise == null
val MainUiState.canModifyExercise: Boolean get() = isAuthenticated && currentExercise != null
val MainUiState.canDeleteExercise: Boolean get() = canModifyExercise
val MainUiState.canGetCycleExercises: Boolean get() = isAuthenticated && currentRoutine != null
val MainUiState.canGetRoutineCycles: Boolean get() = isAuthenticated && currentRoutine != null
