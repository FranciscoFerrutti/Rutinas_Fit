package ar.edu.itba.rutinas_fit.data.network.model

import ar.edu.itba.rutinas_fit.data.model.CycleExercise
import com.google.gson.annotations.SerializedName

class NetworkCycleExercise(
    @SerializedName("cycleId")
    var cycleId: Int?,
    @SerializedName("exerciseId")
    var exerciseId: Int?,
    @SerializedName("order")
    var order: Int,
    @SerializedName("duration")
    var duration: Int,
    @SerializedName("repetitions")
    var repetitions: Int,
    @SerializedName("exercise")
    var exercise: NetworkExercise? = null
) {
    fun asModel(): CycleExercise {
        return CycleExercise(
            cycleId = cycleId,
            exerciseId = exerciseId,
            order = order,
            duration = duration,
            repetitions = repetitions,
            exercise = exercise?.asModel()
        )
    }
}