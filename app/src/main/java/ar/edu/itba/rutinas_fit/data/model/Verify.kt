package ar.edu.itba.rutinas_fit.data.model

import ar.edu.itba.rutinas_fit.data.network.model.NetworkVerify

data class Verify(
    var email: String,
    var code: String
) {
    fun asNetworkModel(): NetworkVerify {
        return NetworkVerify(
            email = email,
            codeString = code
        )
    }
}

