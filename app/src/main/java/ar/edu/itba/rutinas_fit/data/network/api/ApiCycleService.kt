package ar.edu.itba.rutinas_fit.data.network.api

import ar.edu.itba.rutinas_fit.data.network.model.NetworkCycle
import ar.edu.itba.rutinas_fit.data.network.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCycleService {
    @GET("routines/{routineId}/cycles")
    suspend fun getCycles(@Path("routineId") routineId: Int) : Response<NetworkPagedContent<NetworkCycle>>

}
