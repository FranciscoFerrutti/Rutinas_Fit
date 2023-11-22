package ar.edu.itba.rutinas_fit.data.network.model


import ar.edu.itba.rutinas_fit.data.model.Category
import ar.edu.itba.rutinas_fit.data.model.Review
import ar.edu.itba.rutinas_fit.data.model.Routine
import ar.edu.itba.rutinas_fit.data.model.User
import com.google.gson.annotations.SerializedName
import java.util.*

class NetworkReview (
    @SerializedName("score"      ) var score      : Int,
    @SerializedName("review"   )  var review   : String,

) {

    fun asModel() : Review {
        return Review(
            score      = score,
            review  =  review,
        )
    }
}