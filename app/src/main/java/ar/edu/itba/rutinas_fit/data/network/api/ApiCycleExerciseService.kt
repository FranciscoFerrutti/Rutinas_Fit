package ar.edu.itba.rutinas_fit.data.network.api

import ar.edu.itba.rutinas_fit.data.network.model.NetworkCycle
import ar.edu.itba.rutinas_fit.data.network.model.NetworkCycleExercise
import ar.edu.itba.rutinas_fit.data.network.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCycleExerciseService {
    @GET("cycles/{cycleId}/exercises")
    suspend fun getCycleExercises(@Path("cycleId") cycleId: Int) : Response<NetworkPagedContent<NetworkCycleExercise>>
}