package ar.edu.itba.rutinas_fit.data.network

import ar.edu.itba.rutinas_fit.data.network.api.ApiExerciseService
import ar.edu.itba.rutinas_fit.data.network.model.NetworkPagedContent
import ar.edu.itba.rutinas_fit.data.network.model.NetworkExercise

class ExerciseRemoteDataSource(
    private val apiExerciseService: ApiExerciseService
) : RemoteDataSource() {
    suspend fun getExercises(): NetworkPagedContent<NetworkExercise> {
        return handleApiResponse {
            apiExerciseService.getExercises()
        }
    }

    suspend fun getExercise(sportId: Int): NetworkExercise {
        return handleApiResponse {
            apiExerciseService.getExercise(sportId)
        }
    }

    suspend fun addExercise(sport: NetworkExercise): NetworkExercise {
        return handleApiResponse {
            apiExerciseService.addExercise(sport)
        }
    }

    suspend fun modifyExercise(sport: NetworkExercise): NetworkExercise {
        return handleApiResponse {
            apiExerciseService.modifyExercise(sport.id!!, sport)
        }
    }

    suspend fun deleteExercise(sportId: Int) {
        handleApiResponse {
            apiExerciseService.deleteExercise(sportId)
        }
    }
}