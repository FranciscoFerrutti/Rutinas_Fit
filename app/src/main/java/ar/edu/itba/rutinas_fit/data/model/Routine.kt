package ar.edu.itba.rutinas_fit.data.model

import ar.edu.itba.rutinas_fit.data.network.model.NetworkRoutine

data class Routine(
    var id: Int?,
    var name: String,
    var detail: String,
    var date: Int?,
    var isPublic: Boolean,
    var difficulty: String
    //var category: Category, // I think we do not use category
    // do we need metadata?
){
    fun asNetworkModel(): NetworkRoutine {
        return NetworkRoutine(
            id = id,
            name = name,
            detail = detail,
            date = date,
            isPublic = isPublic,
            difficulty = difficulty
        )
    }
}
