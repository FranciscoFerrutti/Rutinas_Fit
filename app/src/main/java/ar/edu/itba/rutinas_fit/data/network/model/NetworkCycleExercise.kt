package ar.edu.itba.rutinas_fit.data.network.model

import android.util.Log
import ar.edu.itba.rutinas_fit.data.model.CycleExercise
import ar.edu.itba.rutinas_fit.data.model.Exercise
import com.google.gson.annotations.SerializedName
import java.util.Date

class NetworkCycleExercise (
    @SerializedName("order"       ) var order       : Int      ,
    @SerializedName("duration"    ) var duration    : Int      ,
    @SerializedName("repetitions" ) var repetitions : Int    ,

){
    fun asModel() : CycleExercise {
        Log.wtf("wtf2",this.repetitions.toString())
        return CycleExercise(
            order           = order           ,
            duration        = duration        ,
            repetitions     = repetitions     ,
            exercise        = Exercise(id = 1, name = "Exercise A1", detail = "Details A1", type = "Type A", date = Date(), metadata=null)
        )
    }
}