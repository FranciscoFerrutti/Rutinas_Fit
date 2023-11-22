package ar.edu.itba.rutinas_fit.data.model

data class Cycle(
    var id: Int? = null,
    var name: String = "",  // Provide a default value for String
    var detail: String = "",  // Provide a default value for String
    var repetitions: Int? = 1,
    var order: Int? = 1,  // Provide a default value for Boolean
    var type: String = ""  // Provide a default value for String
)