package ar.edu.itba.rutinas_fit.data.network.api

import ar.edu.itba.rutinas_fit.data.network.model.NetworkPagedContent
import ar.edu.itba.rutinas_fit.data.network.model.NetworkRoutine

import retrofit2.Response
import retrofit2.http.*

interface ApiRoutineService {
    @GET("routines")
    suspend fun getRoutines(@Query("size") size: Int = 50): Response<NetworkPagedContent<NetworkRoutine>>

    @POST("routines")
    suspend fun addRoutine(@Body routine: NetworkRoutine): Response<NetworkRoutine>

    @GET("routines/{routineId}")
    suspend fun getRoutine(@Path("routineId") routineId: Int): Response<NetworkRoutine>

    @PUT("routines/{routineId}")
    suspend fun modifyRoutine(
        @Path("routineId") routineId: Int,
        @Body routine: NetworkRoutine
    ): Response<NetworkRoutine>

    @DELETE("routines/{routineId}")
    suspend fun deleteRoutine(@Path("routineId") routineId: Int): Response<Unit>

    // we add the cycleRoutines api's endpoints here
    @GET("routines/{routineId}/cycles")
    suspend fun getCycleRoutines(@Path("routineId") routineId: Int): Response<NetworkPagedContent<NetworkRoutine>>

    @GET("routines/{routineId}/cycles/{cycleId}")
    suspend fun getCycleRoutine(
        @Path("routineId") routineId: Int,
        @Path("cycleId") cycleId: Int
    ): Response<NetworkRoutine>
}