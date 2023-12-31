package ar.edu.itba.rutinas_fit.data.network

import ar.edu.itba.rutinas_fit.data.model.Review
import ar.edu.itba.rutinas_fit.data.network.api.ApiRoutineService
import ar.edu.itba.rutinas_fit.data.network.model.NetworkPagedContent
import ar.edu.itba.rutinas_fit.data.network.model.NetworkReview
import ar.edu.itba.rutinas_fit.data.network.model.NetworkRoutine


class RoutineRemoteDataSource(
    private val apiRoutineService: ApiRoutineService
) : RemoteDataSource() {

    suspend fun getRoutines() : NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getRoutines()
        }
    }

    suspend fun getCurrentUserRoutines(): NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getCurrentUserRoutines()
        }
    }

    suspend fun getRoutine(routineId: Int) : NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.getRoutine(routineId)
        }
    }

    suspend fun addRoutine(routine: NetworkRoutine) : NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.addRoutine(routine)
        }
    }

    suspend fun modifyRoutine(routine: NetworkRoutine) : NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.modifyRoutine(routine.id!!, routine)
        }
    }

    suspend fun deleteRoutine(routineId: Int){
        handleApiResponse {
            apiRoutineService.deleteRoutine(routineId)
        }
    }

    suspend fun getFavourites() : NetworkPagedContent<NetworkRoutine>{
        return handleApiResponse {
            apiRoutineService.getFavourites()
        }
    }

    suspend fun deleteFromFavourites(routineId : Int){
        handleApiResponse {
            apiRoutineService.deleteFromFavourites(routineId)
        }
    }

    suspend fun addToFavourites(routineId: Int){
        handleApiResponse {
            apiRoutineService.addToFavourites(routineId)
        }
    }

    suspend fun reviewRoutine(review : NetworkReview, routineId : Int){
        handleApiResponse {
            apiRoutineService.reviewRoutine(routineId,review)
        }
    }
}