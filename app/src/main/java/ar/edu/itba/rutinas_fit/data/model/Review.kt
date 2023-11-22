package ar.edu.itba.rutinas_fit.data.model

import ar.edu.itba.rutinas_fit.data.network.model.NetworkReview
import ar.edu.itba.rutinas_fit.data.network.model.NetworkRoutine
import java.util.*

data class Review(
    var score         : Int,
    var review       : String,
) {

    fun asNetworkModel(): NetworkReview {
        return NetworkReview(
            score = score,
            review = review,
        )
    }
}