package ar.edu.itba.rutinas_fit.ui.sport

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.rutinas_fit.data.DataSourceException
import ar.edu.itba.rutinas_fit.data.model.Error
import ar.edu.itba.rutinas_fit.data.model.Sport
import ar.edu.itba.rutinas_fit.data.repository.SportRepository
import ar.edu.itba.rutinas_fit.data.repository.UserRepository
import ar.edu.itba.rutinas_fit.ui.MainUiState
import ar.edu.itba.rutinas_fit.ui.user.UserUiState
import ar.edu.itba.rutinas_fit.util.SessionManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SportViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val sportRepository: SportRepository
) : ViewModel() {

    var sportState by mutableStateOf(MainUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    fun getSports() = runOnViewModelScope(
        { sportRepository.getSports(true) },
        { state, response -> state.copy(sports = response) }
    )

    fun getSport(sportId: Int) = runOnViewModelScope(
        { sportRepository.getSport(sportId) },
        { state, response -> state.copy(currentSport = response) }
    )

    fun addOrModifySport(sport: Sport) = runOnViewModelScope(
        {
            if (sport.id == null)
                sportRepository.addSport(sport)
            else
                sportRepository.modifySport(sport)
        },
        { state, response ->
            state.copy(
                currentSport = response,
                sports = null
            )
        }
    )

    fun deleteSport(sportId: Int) = runOnViewModelScope(
        { sportRepository.deleteSport(sportId) },
        { state, response ->
            state.copy(
                currentSport = null,
                sports = null
            )
        }
    )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (MainUiState, R) -> MainUiState
    ): Job = viewModelScope.launch {
        sportState = sportState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            sportState = updateState(sportState, response).copy(isFetching = true)
        }.onFailure { e ->
            sportState = sportState.copy(isFetching = true, error = handleError(e))
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