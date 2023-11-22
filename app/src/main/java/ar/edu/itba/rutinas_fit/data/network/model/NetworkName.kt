package ar.edu.itba.rutinas_fit.data.network.model


import ar.edu.itba.rutinas_fit.data.model.Name
import com.google.gson.annotations.SerializedName
import java.util.*

class NetworkName (
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String,
) {

    fun asModel() : Name {
        return Name(
            firstName = firstName,
            lastName = lastName,
        )
    }
}