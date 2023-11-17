package ar.edu.itba.rutinas_fit

import android.app.Application

class RetrofitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: RetrofitApp
            private set
    }
}