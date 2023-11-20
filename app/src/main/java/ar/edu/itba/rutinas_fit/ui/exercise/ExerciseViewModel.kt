package ar.edu.itba.rutinas_fit.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.rutinas_fit.data.DataSourceException
import ar.edu.itba.rutinas_fit.data.repository.ExerciseRepository
import ar.edu.itba.rutinas_fit.data.repository.UserRepository
import ar.edu.itba.rutinas_fit.util.SessionManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ar.edu.itba.rutinas_fit.data.model.Error
import ar.edu.itba.rutinas_fit.data.model.Exercise


class ExerciseViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {

    var uiState by mutableStateOf(ExerciseUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set
    fun getCurrentUser() = runOnViewModelScope(
        { userRepository.getCurrentUser(uiState.currentUser == null) },
        { state, response -> state.copy(currentUser = response) }
    )
    fun getExercises() = runOnViewModelScope(
        { exerciseRepository.getExercises(true) },
        { state, response -> state.copy(exercises = response) }
    )
    fun getExercise(exerciseId: Int) = runOnViewModelScope(
        { exerciseRepository.getExercise(exerciseId) },
        { state, response -> state.copy(currentExercise = response) }
    )
    fun addOrModifyExercise(exercise: Exercise) = runOnViewModelScope(
        {
            if (exercise.id == null)
                exerciseRepository.addExercise(exercise)
            else
                exerciseRepository.modifyExercise(exercise)
        },
        { state, response -> state.copy(
            currentExercise = response,
            exercises = null) }
    )
    fun deleteExercise(exerciseId: Int) = runOnViewModelScope(
        { exerciseRepository.deleteExercise(exerciseId) },
        { state, _ -> state.copy(
            currentExercise = null,
            exercises = null) }
    )
    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (ExerciseUiState, R) -> ExerciseUiState
    ): Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            uiState = updateState(uiState, response).copy(isFetching = false)
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
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