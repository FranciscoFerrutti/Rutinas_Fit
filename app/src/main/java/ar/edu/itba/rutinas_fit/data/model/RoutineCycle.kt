package ar.edu.itba.rutinas_fit.data.model

data class RoutineCycle(
    var routineId: Int?,
    var id: Int?,
    var name: String,
    var detail: String,
    var type: String,
    var order: Int,
    var repetitions: Int
)
