package ar.edu.itba.rutinas_fit.data.model

import ar.edu.itba.rutinas_fit.data.network.model.NetworkExercise
import ar.edu.itba.rutinas_fit.data.network.model.NetworkRoutine
import java.util.*

data class Exercise (

    var id       : Int?,
    var name     : String,
    var detail   : String,
    var type     : String?,
    var date     : Date?,
    var metadata : String?
){
    fun asNetworkModel(): NetworkExercise {
        return NetworkExercise(
            id = id,
            name = name,
            detail = detail,
            date = date,
            type = type,
            metadata = metadata
        )
    }
}