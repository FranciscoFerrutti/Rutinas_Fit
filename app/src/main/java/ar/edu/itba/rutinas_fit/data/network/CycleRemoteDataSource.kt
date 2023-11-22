package ar.edu.itba.rutinas_fit.data.network

import ar.edu.itba.rutinas_fit.data.network.api.ApiCycleService
import ar.edu.itba.rutinas_fit.data.network.model.NetworkCycle
import ar.edu.itba.rutinas_fit.data.network.model.NetworkPagedContent

class CycleRemoteDataSource(
    private val apiCycleService: ApiCycleService
) : RemoteDataSource() {

    suspend fun getCycles(routineId: Int) : NetworkPagedContent<NetworkCycle> {
        return handleApiResponse {
            apiCycleService.getCycles(routineId)
        }
    }

}