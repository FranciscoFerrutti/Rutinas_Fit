package ar.edu.itba.rutinas_fit.data.network.api


import ar.edu.itba.rutinas_fit.data.network.model.NetworkPagedContent
import ar.edu.itba.rutinas_fit.data.network.model.NetworkReview
import ar.edu.itba.rutinas_fit.data.network.model.NetworkRoutine
import retrofit2.Response
import retrofit2.http.*

interface ApiRoutineService {

    @GET("routines")
    suspend fun getRoutines() : Response<NetworkPagedContent<NetworkRoutine>>

    @GET("users/current/routines")
    suspend fun getCurrentUserRoutines() : Response<NetworkPagedContent<NetworkRoutine>>

    @POST("routines")
    suspend fun addRoutine(@Body routine: NetworkRoutine) : Response<NetworkRoutine>

    @GET("routines/{routineId}")
    suspend fun getRoutine(@Path("routineId") routineId: Int) : Response<NetworkRoutine>

    @PUT("routines/{routineId}")
    suspend fun modifyRoutine(@Path("routineId") routineId: Int, @Body routine: NetworkRoutine) : Response<NetworkRoutine>

    @DELETE("routines/{routineId}")
    suspend fun deleteRoutine(@Path("routineId") routineId: Int) : Response<Unit>

    @GET("favourites")
    suspend fun getFavourites(): Response<NetworkPagedContent<NetworkRoutine>>

    @POST("favourites/{routineId}")
    suspend fun addToFavourites(@Path("routineId") routineId: Int) : Response<Unit>

    @DELETE("favourites/{routineId}")
    suspend fun deleteFromFavourites(@Path("routineId") routineId: Int) : Response<Unit>

    @POST("reviews/{routineId}")
    suspend fun reviewRoutine(@Path("routineId") routineId: Int, @Body review : NetworkReview) : Response<Unit>

}