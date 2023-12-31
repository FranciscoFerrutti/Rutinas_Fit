package ar.edu.itba.rutinas_fit.data.network.model


import ar.edu.itba.rutinas_fit.data.model.Category
import ar.edu.itba.rutinas_fit.data.model.Routine
import ar.edu.itba.rutinas_fit.data.model.User
import com.google.gson.annotations.SerializedName
import java.util.*

class NetworkRoutine (
    @SerializedName("id"         ) var id         : Int,
    @SerializedName("name"       ) var name       : String,
    @SerializedName("detail"     ) var detail     : String,
    @SerializedName("date"       ) var date       : Date?,
    @SerializedName("score"      ) var score      : Int?,
    @SerializedName("isPublic"   ) var isPublic   : Boolean?,
    @SerializedName("difficulty" ) var difficulty : String?,
    @SerializedName("user"       ) var user       : User?,
    @SerializedName("category"   ) var category   : Category?,
    @SerializedName("metadata"   ) var metadata   : String?
) {

    fun asModel() : Routine {
        return Routine(
            id =         id,
            name =       name,
            detail =     detail,
            date  =      date,
            score      = score,
            isPublic  =  isPublic,
            difficulty = difficulty,
            user       = user,
            category =   category,
            metadata =   metadata
        )
    }
}