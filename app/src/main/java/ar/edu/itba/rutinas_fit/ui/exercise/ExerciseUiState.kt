package ar.edu.itba.rutinas_fit.ui.exercise

import ar.edu.itba.rutinas_fit.data.model.Error
import ar.edu.itba.rutinas_fit.data.model.Exercise
import ar.edu.itba.rutinas_fit.data.model.User
import ar.edu.itba.rutinas_fit.ui.user.UserUiState

data class ExerciseUiState(
    val userUiState: UserUiState = UserUiState(),
    val exercises: List<Exercise>? = null,
    val currentExercise: Exercise? = null,
    val error: Error? = null
)

val ExerciseUiState.canGetCurrentUser: Boolean get() = userUiState.isAuthenticated
val ExerciseUiState.canGetAllExercises: Boolean get() = userUiState.isAuthenticated
val ExerciseUiState.canGetCurrentExercise: Boolean get() = userUiState.isAuthenticated && currentExercise != null
val ExerciseUiState.canAddExercise: Boolean get() = userUiState.isAuthenticated && currentExercise == null
val ExerciseUiState.canModifyExercise: Boolean get() = userUiState.isAuthenticated && currentExercise != null
val ExerciseUiState.canDeleteExercise: Boolean get() = canModifyExercise
