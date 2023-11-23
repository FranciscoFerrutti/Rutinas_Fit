package ar.edu.itba.rutinas_fit.data.network.model

import android.util.Log
import ar.edu.itba.rutinas_fit.data.model.CycleExercise
import ar.edu.itba.rutinas_fit.data.model.Exercise
import com.google.gson.annotations.SerializedName
import java.util.Date

class NetworkCycleExercise(
    @SerializedName("order") var order: Int,
    @SerializedName("duration") var duration: Int,
    @SerializedName("repetitions") var repetitions: Int,
    @SerializedName("exercise") var exerciseWrapper: ExerciseWrapper
) {
    data class ExerciseWrapper(
        @SerializedName("id") var id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("detail") var detail: String,
        @SerializedName("type") var type: String,
        @SerializedName("date") var date: Long,
        @SerializedName("metadata") var metadata: MetadataWrapper?
    )

    data class MetadataWrapper(
        @SerializedName("type") var type: String
    )

    fun asModel(): CycleExercise {
        return CycleExercise(
            order = order,
            duration = duration,
            repetitions = repetitions,
            exercise = Exercise(
                id = exerciseWrapper.id,
                name = exerciseWrapper.name,
                detail = exerciseWrapper.detail,
                type = exerciseWrapper.type,
                date = Date(exerciseWrapper.date),
                metadata = exerciseWrapper.metadata?.type
            )
        )
    }
}