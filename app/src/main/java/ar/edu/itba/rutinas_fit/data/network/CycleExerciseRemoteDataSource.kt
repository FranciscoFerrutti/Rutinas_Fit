package ar.edu.itba.rutinas_fit.data.network

import ar.edu.itba.rutinas_fit.data.network.api.ApiCycleExerciseService
import ar.edu.itba.rutinas_fit.data.network.api.ApiCycleService
import ar.edu.itba.rutinas_fit.data.network.model.NetworkCycle
import ar.edu.itba.rutinas_fit.data.network.model.NetworkCycleExercise
import ar.edu.itba.rutinas_fit.data.network.model.NetworkPagedContent

class CycleExerciseRemoteDataSource(
    private val apiCycleExerciseService: ApiCycleExerciseService
) : RemoteDataSource() {

    suspend fun getCycleExercises(cycleId: Int) : NetworkPagedContent<NetworkCycleExercise> {
        return handleApiResponse {
            apiCycleExerciseService.getCycleExercises(cycleId)
        }
    }

}