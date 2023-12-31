package ar.edu.itba.rutinas_fit.data.network.model

import ar.edu.itba.rutinas_fit.data.model.Exercise
import com.google.gson.annotations.SerializedName
import java.util.Date

class NetworkExercise(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String,
    @SerializedName("detail")
    var detail: String,
    @SerializedName("type")
    var type: String?,
    @SerializedName("date")
    var date: Date? = null,
    @SerializedName("metadata")
    var metadata: String? = null // we do not use metadata
) {
    fun asModel(): Exercise {
        return Exercise(
            id=id,
            name = name,
            detail = detail,
            type = type,
            date = date,
            metadata=""
        )
    }
}