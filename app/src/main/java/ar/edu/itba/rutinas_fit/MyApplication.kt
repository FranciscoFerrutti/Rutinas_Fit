package ar.edu.itba.rutinas_fit

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import ar.edu.itba.rutinas_fit.data.network.CycleExerciseRemoteDataSource
import ar.edu.itba.rutinas_fit.data.network.CycleRemoteDataSource
import ar.edu.itba.rutinas_fit.data.network.api.*
import ar.edu.itba.rutinas_fit.data.network.RoutineRemoteDataSource
import ar.edu.itba.rutinas_fit.data.network.SportRemoteDataSource
import ar.edu.itba.rutinas_fit.data.network.UserRemoteDataSource
import ar.edu.itba.rutinas_fit.data.network.api.RetrofitClient
import ar.edu.itba.rutinas_fit.data.repository.CycleExerciseRepository
import ar.edu.itba.rutinas_fit.data.repository.CycleRepository

import ar.edu.itba.rutinas_fit.data.repository.RoutineRepository
import ar.edu.itba.rutinas_fit.data.repository.SportRepository
import ar.edu.itba.rutinas_fit.data.repository.UserRepository
import ar.edu.itba.rutinas_fit.util.SessionManager

class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(RetrofitClient.getApiUserService(this), sessionManager)

    private val sportRemoteDataSource: SportRemoteDataSource
        get() = SportRemoteDataSource(RetrofitClient.getApiSportService(this))

    private val routineRemoteDataSource: RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(RetrofitClient.getApiRoutineService(this))

    private val cycleRemoteDataSource: CycleRemoteDataSource
        get() = CycleRemoteDataSource(RetrofitClient.getApiCycleService(this))
    private val cycleExerciseRemoteDataSource: CycleExerciseRemoteDataSource
        get() = CycleExerciseRemoteDataSource(RetrofitClient.getApiCycleExerciseService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val sportRepository: SportRepository
        get() = SportRepository(sportRemoteDataSource)

    val routineRepository: RoutineRepository
        get() = RoutineRepository(routineRemoteDataSource)
    val cycleRepository: CycleRepository
        get() = CycleRepository(cycleRemoteDataSource)

    val cycleExerciseRepository: CycleExerciseRepository
        get() = CycleExerciseRepository(cycleExerciseRemoteDataSource)
}

fun isDeviceInLandscape(context: Context): Boolean {
    val orientation = context.resources.configuration.orientation
    return orientation == Configuration.ORIENTATION_LANDSCAPE
}