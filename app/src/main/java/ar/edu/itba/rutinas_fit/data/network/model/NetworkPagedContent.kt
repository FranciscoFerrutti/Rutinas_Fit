package ar.edu.itba.rutinas_fit.data.network.model

import com.google.gson.annotations.SerializedName

class NetworkPagedContent<T> (

    @SerializedName("totalCount")
    var totalCount: Int,
    @SerializedName("orderBy")
    var orderBy: String? = null,
    @SerializedName("direction")
    var direction: String? = null,
    @SerializedName("content")
    var content : List<T> = arrayListOf(),
    @SerializedName("size")
    var size : Int,
    @SerializedName("page")
    var page : Int,
    @SerializedName("isLastPage")
    var isLastPage : Boolean

)