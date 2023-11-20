package ar.edu.itba.rutinas_fit.data.network.model

import ar.edu.itba.rutinas_fit.data.model.Routine
import com.google.gson.annotations.SerializedName

class NetworkRoutine(
    @SerializedName("id")
    var id : Int? = null,
    @SerializedName("name")
    var name : String,
    @SerializedName("detail")
    var detail : String,
    @SerializedName("date")
    var date : Int? = null,
    @SerializedName("score")
    var score : Int? = null,
    @SerializedName("isPublic")
    var isPublic : Boolean,
    @SerializedName("difficulty" )
    var difficulty : String,
    @SerializedName("user")
    var user: NetworkUser? = null,
    @SerializedName("category")
    var category: NetworkCategory? = null, // we do not use category
    @SerializedName("metadata")
    var metadata: NetworkRoutineMetadata? = null // we do not use metadata
){
    fun asModel(): Routine {
        return Routine(
            id = id,
            name = name,
            detail = detail,
            date = date,
            isPublic = isPublic,
            difficulty = difficulty
        )
    }
}
