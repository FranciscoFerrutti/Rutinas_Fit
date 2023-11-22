package ar.edu.itba.rutinas_fit.data.network.model

import ar.edu.itba.rutinas_fit.data.model.Cycle
import com.google.gson.annotations.SerializedName

data class NetworkCycle (
    @SerializedName("id"          ) var id          : Int   ,
    @SerializedName("name"        ) var name        : String ,
    @SerializedName("detail"      ) var detail      : String ,
    @SerializedName("type"        ) var type        : String? = null,
    @SerializedName("order"       ) var order       : Int    ,
    @SerializedName("repetitions" ) var repetitions : Int    ,
    @SerializedName("metadata"    ) var metadata    : String? = null
){
    fun asModel() : Cycle {
        return Cycle(
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