package ar.edu.itba.rutinas_fit.data.model

import ar.edu.itba.rutinas_fit.data.network.model.NetworkCycle

data class Cycle (

    var id          : Int    ,
    var name        : String ,
    var detail      : String ,
    var type        : String? ,
    var order       : Int   ,
    var repetitions : Int    ,
    var metadata    : String?

){
    fun asNetworkModel() : NetworkCycle {
        return NetworkCycle(
            id          =id         ,
            name        =name       ,
            detail      =detail     ,
            type        =type       ,
            order       =order      ,
            repetitions =repetitions,
            metadata    =metadata
        )
    }
}
