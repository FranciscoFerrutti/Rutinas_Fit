package ar.edu.itba.rutinas_fit.data.model

import ar.edu.itba.rutinas_fit.data.network.model.NetworkSport


class Sport(
    var id: Int? = null,
    var name: String,
    var detail: String? = null
) {
    fun asNetworkModel(): NetworkSport {
        return NetworkSport(
            id = id,
            name = name,
            detail = detail
        )
    }
}
