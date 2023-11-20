package ar.edu.itba.rutinas_fit.ui.exercise

import ar.edu.itba.rutinas_fit.data.model.Error
import ar.edu.itba.rutinas_fit.data.model.Exercise
import ar.edu.itba.rutinas_fit.data.model.User

data class ExerciseUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val exercises: List<Exercise>? = null,
    val currentExercise: Exercise? = null,
    val error: Error? = null
)

val ExerciseUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val ExerciseUiState.canGetAllExercises: Boolean get() = isAuthenticated
val ExerciseUiState.canGetCurrentExercise: Boolean get() = isAuthenticated && currentExercise != null
val ExerciseUiState.canAddExercise: Boolean get() = isAuthenticated && currentExercise == null
val ExerciseUiState.canModifyExercise: Boolean get() = isAuthenticated && currentExercise != null
val ExerciseUiState.canDeleteExercise: Boolean get() = canModifyExercise
