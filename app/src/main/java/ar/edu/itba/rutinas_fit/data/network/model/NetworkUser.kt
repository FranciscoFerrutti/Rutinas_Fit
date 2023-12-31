package ar.edu.itba.rutinas_fit.data.network.model


import ar.edu.itba.rutinas_fit.data.model.User
import com.google.gson.annotations.SerializedName
import java.util.Date

class NetworkUser (

    @SerializedName("id")
    var id: Int,
    @SerializedName("username")
    var username: String,
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String,
    @SerializedName("gender")
    var gender: String? = null,
    @SerializedName("birthdate")
    var birthdate: Date? = null,
    @SerializedName("email")
    var email: String,
    @SerializedName("phone")
    var phone: String? = null,
    @SerializedName("avatarUrl")
    var avatarUrl: String? = null,
    @SerializedName("metadata")
    var metadata: NetworkUserMetadata? = null,
    @SerializedName("date")
    var date: Date? = null,
    @SerializedName("lastActivity")
    var lastActivity: Date? = null,
    @SerializedName("verified")
    var verified: Boolean
) {

    fun asModel() : User {
        return User(
            id = id,
            username = username,
            firstName = firstName,
            lastName = lastName,
            email = email,
            lastActivity = lastActivity,
            avatarUrl = avatarUrl
        )
    }
}

