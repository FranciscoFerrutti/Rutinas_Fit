package ar.edu.itba.rutinas_fit.data.network.model

class NetworkCycleRoutine(
    var routineId: Int?,
    var id: Int?,
    var name: String,
    var detail: String,
    var type: String,
    var order: Int,
    var repetitions: Int,
    var metadata: NetworkCycleRoutineMetadata? = null
) {
    fun asModel(): ar.edu.itba.rutinas_fit.data.model.RoutineCycle {
        return ar.edu.itba.rutinas_fit.data.model.RoutineCycle(
            routineId = routineId,
            id = id,
            name = name,
            detail = detail,
            type = type,
            order = order,
            repetitions = repetitions
        )
    }
}