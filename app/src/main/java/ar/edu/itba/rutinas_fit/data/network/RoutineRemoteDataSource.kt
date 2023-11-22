package ar.edu.itba.rutinas_fit.data.network

import ar.edu.itba.rutinas_fit.data.network.api.ApiRoutineService
import ar.edu.itba.rutinas_fit.data.network.model.NetworkPagedContent
import ar.edu.itba.rutinas_fit.data.network.model.NetworkRoutine

class RoutineRemoteDataSource(
    private val apiRoutineService: ApiRoutineService
) : RemoteDataSource() {
    suspend fun getRoutines(): NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getRoutines()
        }
    }

    suspend fun getRoutine(sportId: Int): NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.getRoutine(sportId)
        }
    }

    suspend fun addRoutine(sport: NetworkRoutine): NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.addRoutine(sport)
        }
    }

    suspend fun modifyRoutine(sport: NetworkRoutine): NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.modifyRoutine(sport.id!!, sport)
        }
    }

    suspend fun deleteRoutine(sportId: Int) {
        handleApiResponse {
            apiRoutineService.deleteRoutine(sportId)
        }
    }

    // we add the cycleRoutines api's endpoints here
    suspend fun getCycleRoutines(routineId: Int): NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getCycleRoutines(routineId)
        }
    }

    suspend fun getCycleRoutine(routineId: Int, cycleId: Int): NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.getCycleRoutine(routineId, cycleId)
        }
    }
}