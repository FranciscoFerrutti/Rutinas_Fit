package ar.edu.itba.rutinas_fit

import android.app.Application
import ar.edu.itba.rutinas_fit.data.network.ExerciseRemoteDataSource
import ar.edu.itba.rutinas_fit.data.network.RoutineRemoteDataSource
import ar.edu.itba.rutinas_fit.data.network.SportRemoteDataSource
import ar.edu.itba.rutinas_fit.data.network.UserRemoteDataSource
import ar.edu.itba.rutinas_fit.data.network.api.RetrofitClient
import ar.edu.itba.rutinas_fit.data.repository.ExerciseRepository
import ar.edu.itba.rutinas_fit.data.repository.RoutineRepository
import ar.edu.itba.rutinas_fit.data.repository.SportRepository
import ar.edu.itba.rutinas_fit.data.repository.UserRepository
import ar.edu.itba.rutinas_fit.util.SessionManager

class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getApiUserService(this))

    private val sportRemoteDataSource: SportRemoteDataSource
        get() = SportRemoteDataSource(RetrofitClient.getApiSportService(this))

    private val routineRemoteDataSource: RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(RetrofitClient.getApiRoutineService(this))

    private val exerciseRemoteDataSource: ExerciseRemoteDataSource
        get() = ExerciseRemoteDataSource(RetrofitClient.getApiExerciseService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val sportRepository: SportRepository
        get() = SportRepository(sportRemoteDataSource)

    val routineRepository: RoutineRepository
        get() = RoutineRepository(routineRemoteDataSource)

    val exerciseRepository: ExerciseRepository
        get() = ExerciseRepository(exerciseRemoteDataSource)
}