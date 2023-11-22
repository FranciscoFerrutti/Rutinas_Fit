package ar.edu.itba.rutinas_fit.data.repository

import ar.edu.itba.rutinas_fit.data.model.Exercise
import ar.edu.itba.rutinas_fit.data.network.ExerciseRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ExerciseRepository(
    private val remoteDataSource: ExerciseRemoteDataSource
) {
    // Mutex to make writes to cached values thread-safe.
    private val exercisesMutex = Mutex()
    // Cache of the latest exercises got from the network.
    private var exercises: List<Exercise> = emptyList()

    suspend fun getExercises(refresh: Boolean = false): List<Exercise> {
        if (refresh || exercises.isEmpty()) {
            val result = remoteDataSource.getExercises()
            // Thread-safe write to exercises
            exercisesMutex.withLock {
                this.exercises = result.content.map { it.asModel() }
            }
        }

        return exercisesMutex.withLock { this.exercises }
    }

    suspend fun getExercise(exerciseId: Int) : Exercise {
        return remoteDataSource.getExercise(exerciseId).asModel()
    }

    suspend fun addExercise(exercise: Exercise) : Exercise {
        val newExercise = remoteDataSource.addExercise(exercise.asNetworkModel()).asModel()
        exercisesMutex.withLock {
            this.exercises = emptyList()
        }
        return newExercise
    }

    suspend fun modifyExercise(exercise: Exercise) : Exercise {
        val updatedExercise = remoteDataSource.modifyExercise(exercise.asNetworkModel()).asModel()
        exercisesMutex.withLock {
            this.exercises = emptyList()
        }
        return updatedExercise
    }

    suspend fun deleteExercise(exerciseId: Int) {
        remoteDataSource.deleteExercise(exerciseId)
        exercisesMutex.withLock {
            this.exercises = emptyList()
        }
    }

    // we add the cycleExercises api's endpoints here

    suspend fun getCycleExercises(cycleId: Int): List<Exercise> {
        val result = remoteDataSource.getCycleExercises(cycleId)
        return result.content.map { it.asModel() }
    }

    suspend fun getCycleExercise(cycleId: Int, exerciseId: Int): Exercise {
        return remoteDataSource.getCycleExercise(cycleId, exerciseId).asModel()
    }

}