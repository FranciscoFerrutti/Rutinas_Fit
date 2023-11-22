package ar.edu.itba.rutinas_fit.data.model

import ar.edu.itba.rutinas_fit.data.network.model.NetworkCycleExercise

data class CycleExercise(
    var cycleId: Int?,
    var exerciseId: Int?,
    var order: Int,
    var duration: Int,
    var repetitions: Int,
    var exercise: Exercise?
){
    fun asNetworkModel(): NetworkCycleExercise {
        return NetworkCycleExercise(
            cycleId = cycleId,
            exerciseId = exerciseId,
            order = order,
            duration = duration,
            repetitions = repetitions,
            exercise = exercise?.asNetworkModel()
        )
    }
}
