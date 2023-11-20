package ar.edu.itba.rutinas_fit.ui.sport

import ar.edu.itba.rutinas_fit.data.model.Error
import ar.edu.itba.rutinas_fit.data.model.Sport
import ar.edu.itba.rutinas_fit.ui.user.UserUiState

data class SportUiState(
    val userUiState: UserUiState = UserUiState(),
    val sports: List<Sport>? = null,
    val currentSport: Sport? = null,
    val error: Error? = null
)

val SportUiState.canGetCurrentUser: Boolean get() = userUiState.isAuthenticated
val SportUiState.canGetAllSports: Boolean get() = userUiState.isAuthenticated
val SportUiState.canGetCurrentSport: Boolean get() = userUiState.isAuthenticated && currentSport != null
val SportUiState.canAddSport: Boolean get() = userUiState.isAuthenticated //&& currentSport == null
val SportUiState.canModifySport: Boolean get() = userUiState.isAuthenticated && currentSport != null
val SportUiState.canDeleteSport: Boolean get() = canModifySport
