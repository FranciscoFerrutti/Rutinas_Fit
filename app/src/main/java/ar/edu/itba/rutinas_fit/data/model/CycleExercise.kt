package ar.edu.itba.rutinas_fit.data.model

import ar.edu.itba.rutinas_fit.data.network.model.NetworkCycle
import ar.edu.itba.rutinas_fit.data.network.model.NetworkCycleExercise
import com.google.gson.annotations.SerializedName

data class CycleExercise(
    var order       : Int     ,
    var duration    : Int     ,
    var repetitions : Int     ,
    var exercise    : Exercise
){
    fun asNetworkModel() : NetworkCycleExercise {
        return NetworkCycleExercise(
            order           = order           ,
            duration        = duration        ,
            repetitions     = repetitions     ,

        )
    }
}
