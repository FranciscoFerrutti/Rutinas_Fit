package ar.edu.itba.rutinas_fit.ui.main

import androidx.compose.runtime.mutableStateOf
import ar.edu.itba.rutinas_fit.data.model.Error
import ar.edu.itba.rutinas_fit.data.model.Sport
import ar.edu.itba.rutinas_fit.data.model.User
import ar.edu.itba.rutinas_fit.ui.user.UserUiState

data class MainUiState(
    val isAuthenticated: Boolean = mutableStateOf(UserUiState().isAuthenticated).value,
    val isFetching: Boolean = mutableStateOf(UserUiState().isFetching).value,
    val currentUser: User? = null,
    val sports: List<Sport>? = null,
    val currentSport: Sport? = null,
    val error: Error? = null
)

val MainUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val MainUiState.canGetAllSports: Boolean get() = isAuthenticated
val MainUiState.canGetCurrentSport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canAddSport: Boolean get() = isAuthenticated && currentSport == null
val MainUiState.canModifySport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canDeleteSport: Boolean get() = canModifySport
