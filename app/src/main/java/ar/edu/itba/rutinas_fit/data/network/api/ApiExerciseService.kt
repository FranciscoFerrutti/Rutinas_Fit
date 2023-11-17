package ar.edu.itba.rutinas_fit.data.network.api

import ar.edu.itba.rutinas_fit.data.network.model.NetworkPagedContent
import ar.edu.itba.rutinas_fit.data.network.model.NetworkExercise
import retrofit2.Response
import retrofit2.http.*
interface ApiExerciseService {
    @GET("exercises")
    suspend fun getExercises(@Query("size") size: Int = 50): Response<NetworkPagedContent<NetworkExercise>>

    @POST("exercises")
    suspend fun addExercise(@Body exercise: NetworkExercise): Response<NetworkExercise>

    @GET("exercises/{exerciseId}")
    suspend fun getExercise(@Path("exerciseId") exerciseId: Int): Response<NetworkExercise>

    @PUT("exercises/{exerciseId}")
    suspend fun modifyExercise(
        @Path("exerciseId") exerciseId: Int,
        @Body exercise: NetworkExercise
    ): Response<NetworkExercise>

    @DELETE("exercises/{exerciseId}")
    suspend fun deleteExercise(@Path("exerciseId") exerciseId: Int): Response<Unit>
}