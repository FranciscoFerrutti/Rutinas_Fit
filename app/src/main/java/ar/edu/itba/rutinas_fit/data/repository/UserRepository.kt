package ar.edu.itba.rutinas_fit.data.repository


import ar.edu.itba.rutinas_fit.data.model.Name
import ar.edu.itba.rutinas_fit.data.model.SignUp
import ar.edu.itba.rutinas_fit.data.model.User
import ar.edu.itba.rutinas_fit.data.model.Verify
import ar.edu.itba.rutinas_fit.data.network.UserRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val currentUserMutex = Mutex()
    // Cache of the current user got from the network.
    private var currentUser: User? = null

    suspend fun login(username: String, password: String) {
        remoteDataSource.login(username, password)
    }

    suspend fun logout() {
        remoteDataSource.logout()
    }

    suspend fun signUp(data: SignUp){
        remoteDataSource.signUp(data.asNetworkModel())
    }

    suspend fun verify(data: Verify){
        remoteDataSource.verify(data.asNetworkModel())
    }


    suspend fun getCurrentUser(refresh: Boolean) : User? {//si refresh es true se llama a la api, sino no
        if (refresh || currentUser == null) {
            val result = remoteDataSource.getCurrentUser()
            // Thread-safe write to latestNews
            currentUserMutex.withLock {
                this.currentUser = result.asModel()
            }
        }

        return currentUserMutex.withLock { this.currentUser }
    }

    suspend fun modifyUser(newName: Name){
        remoteDataSource.modifyUser(newName.asNetworkModel())

        currentUserMutex.withLock {
            this.currentUser = null
        }
    }
}

