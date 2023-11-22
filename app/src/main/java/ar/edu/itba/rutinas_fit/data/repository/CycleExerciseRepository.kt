package ar.edu.itba.rutinas_fit.data.repository

import ar.edu.itba.rutinas_fit.data.model.Cycle
import ar.edu.itba.rutinas_fit.data.model.CycleExercise
import ar.edu.itba.rutinas_fit.data.network.CycleExerciseRemoteDataSource
import ar.edu.itba.rutinas_fit.data.network.CycleRemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CycleExerciseRepository(
    private val remoteDataSource: CycleExerciseRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val cyclesMutex = Mutex()
    // Cache of the latest routines got from the network.
    private var cycleExercises: List<CycleExercise> = emptyList()

    suspend fun getCycleExercises(cycleId: Int): List<CycleExercise> {

            val result = remoteDataSource.getCycleExercises(cycleId)
            // Thread-safe write to latestNews
            cyclesMutex.withLock {
                this.cycleExercises = result.content.map { it.asModel() }
            }

        return cyclesMutex.withLock { this.cycleExercises }
    }
}
