package ar.edu.itba.rutinas_fit.data.model

import ar.edu.itba.rutinas_fit.data.network.model.NetworkName

class Name(
    var firstName: String,
    var lastName: String,
) {
    fun asNetworkModel(): NetworkName {
        return NetworkName(
            firstName = firstName,
            lastName = lastName,
        )
    }
}
