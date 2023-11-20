package ar.edu.itba.rutinas_fit.ui.user

import ar.edu.itba.rutinas_fit.data.model.User
import ar.edu.itba.rutinas_fit.data.model.Error

data class UserUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val error: Error? = null
)

val UserUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val UserUiState.canLogin: Boolean get() = !isAuthenticated
val UserUiState.canLogout: Boolean get() = isAuthenticated
