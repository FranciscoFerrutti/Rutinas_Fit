package ar.edu.itba.rutinas_fit.data.model

data class Error(
    val code: Int?,
    val message: String,
    val description: List<String>? = null
)
