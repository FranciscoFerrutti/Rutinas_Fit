package ar.edu.itba.rutinas_fit.data.network.model


import ar.edu.itba.rutinas_fit.data.model.Name
import com.google.gson.annotations.SerializedName
import java.util.*

class NetworkName (
    @SerializedName("username")
    var username: String,
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String,
    @SerializedName("email")
    var email: String,
) {

    fun asModel() : Name {
        return Name(
            username = username,
            firstName = firstName,
            lastName = lastName,
            email = email,
        )
    }
}