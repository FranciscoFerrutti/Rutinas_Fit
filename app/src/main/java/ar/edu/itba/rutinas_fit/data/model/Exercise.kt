package ar.edu.itba.rutinas_fit.data.model

import ar.edu.itba.rutinas_fit.data.network.model.NetworkExercise

data class Exercise(
    var id: Int?,
    var name: String,
    var detail: String,
    var type: String,
    var date: Int?,
    // do we need metadata?
) {
    fun asNetworkModel(): NetworkExercise {
        return NetworkExercise(
            id = id,
            name = name,
            detail = detail,
            type = type,
            date = date
        )
    }
}
