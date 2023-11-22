package ar.edu.itba.rutinas_fit.data.network.model

import ar.edu.itba.rutinas_fit.data.model.CycleExercise
import ar.edu.itba.rutinas_fit.data.model.Exercise
import com.google.gson.annotations.SerializedName

class NetworkCycleExercise (
    @SerializedName("order"       ) var order       : Int      ,
    @SerializedName("duration"    ) var duration    : Int      ,
    @SerializedName("repetitions" ) var repetitions : Int    ,
    @SerializedName("exercise"    ) var exercise    : Exercise

){
    fun asModel() : CycleExercise {
        return CycleExercise(
            order           =order           ,
            duration        =duration        ,
            repetitions     =repetitions     ,
            exercise        =exercise

        )
    }
}