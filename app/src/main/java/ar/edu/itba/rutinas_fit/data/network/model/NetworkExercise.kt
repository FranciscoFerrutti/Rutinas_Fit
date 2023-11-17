package ar.edu.itba.rutinas_fit.data.network.model

import ar.edu.itba.rutinas_fit.data.model.Exercise
import com.google.gson.annotations.SerializedName

class NetworkExercise(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String,
    @SerializedName("detail")
    var detail: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("date")
    var date: Int?,
    @SerializedName("metadata")
    var metadata: NetworkExerciseMetadata? = null // we do not use metadata
) {
    fun asModel(): Exercise {
        return Exercise(
            id=id,
            name = name,
            detail = detail,
            type = type,
            date = date
        )
    }
}