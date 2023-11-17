package ar.edu.itba.rutinas_fit

import android.app.Application
import ar.edu.itba.rutinas_fit.data.network.SportRemoteDataSource
import ar.edu.itba.rutinas_fit.data.network.UserRemoteDataSource
import ar.edu.itba.rutinas_fit.data.network.api.RetrofitClient
import ar.edu.itba.rutinas_fit.data.repository.SportRepository
import ar.edu.itba.rutinas_fit.data.repository.UserRepository
import ar.edu.itba.rutinas_fit.util.SessionManager

class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getApiUserService(this))

    private val sportRemoteDataSource: SportRemoteDataSource
        get() = SportRemoteDataSource(RetrofitClient.getApiSportService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val sportRepository: SportRepository
        get() = SportRepository(sportRemoteDataSource)
}