package ar.edu.itba.rutinas_fit.data.repository

import ar.edu.itba.rutinas_fit.data.model.Routine
import ar.edu.itba.rutinas_fit.data.network.RoutineRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineRepository(
    private val remoteDataSource: RoutineRemoteDataSource
) {
    // Mutex to make writes to cached values thread-safe.
    private val routinesMutex = Mutex()
    // Cache of the latest routines got from the network.
    private var routines: List<Routine> = emptyList()
    
    suspend fun getRoutines(refresh: Boolean = false): List<Routine> {
        if (refresh || routines.isEmpty()) {
            val result = remoteDataSource.getRoutines()
            // Thread-safe write to routines
            routinesMutex.withLock {
                this.routines = result.content.map { it.asModel() }
            }
        }

        return routinesMutex.withLock { this.routines }
    }

    suspend fun getRoutine(routineId: Int) : Routine {
        return remoteDataSource.getRoutine(routineId).asModel()
    }
    
    suspend fun addRoutine(routine: Routine) : Routine {
        val newRoutine = remoteDataSource.addRoutine(routine.asNetworkModel()).asModel()
        routinesMutex.withLock {
            this.routines = emptyList()
        }
        return newRoutine
    }

    suspend fun modifyRoutine(routine: Routine) : Routine {
        val updatedRoutine = remoteDataSource.modifyRoutine(routine.asNetworkModel()).asModel()
        routinesMutex.withLock {
            this.routines = emptyList()
        }
        return updatedRoutine
    }

    suspend fun deleteRoutine(routineId: Int) {
        remoteDataSource.deleteRoutine(routineId)
        routinesMutex.withLock {
            this.routines = emptyList()
        }
    }

    // we add the cycleRoutines api's endpoints here

    suspend fun getCycleRoutines(routineId: Int): List<Routine> {
        val result = remoteDataSource.getCycleRoutines(routineId)
        return result.content.map { it.asModel() }
    }

    suspend fun getCycleRoutine(routineId: Int, cycleId: Int): Routine {
        return remoteDataSource.getCycleRoutine(routineId, cycleId).asModel()
    }
    
}