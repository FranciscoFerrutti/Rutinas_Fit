package ar.edu.itba.rutinas_fit.data.model

import ar.edu.itba.rutinas_fit.data.network.model.NetworkName

class Name(
    var username: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    var image: String,
) {
    fun asNetworkModel(): NetworkName {
        return NetworkName(
            username = username,
            firstName = firstName,
            lastName = lastName,
            email = email,
            image = image,
        )
    }
}
