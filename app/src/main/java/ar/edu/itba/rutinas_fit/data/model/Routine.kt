package ar.edu.itba.rutinas_fit.data.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ar.edu.itba.rutinas_fit.R
import ar.edu.itba.rutinas_fit.data.network.model.NetworkRoutine
import com.google.gson.annotations.SerializedName
import java.util.Date


data class Routine(
    var id         : Int,
    var name       : String,
    var detail     : String,
    var date       : Date?,
    var score      : Int?,
    var isPublic   : Boolean?,
    var difficulty : String?,
    var user       : User?,
    var category   : Category?,
    var metadata   : String?
) {
    fun asNetworkModel(): NetworkRoutine {
        return NetworkRoutine(
            id = id,
            name = name,
            detail = detail,
            date = date,
            score = score,
            isPublic = isPublic,
            difficulty = difficulty,
            user = user,
            category = category,
            metadata = metadata
        )
    }
}

fun List<Routine>.sortByCondition(comparator: Comparator<Routine>): List<Routine> {

    return sortedWith(comparator)
}

@Composable
fun getSortedRoutines(routines: List<Routine>, selectedOption: String): List<Routine> {
    return when (selectedOption) {
        stringResource(id = R.string.date) -> routines.sortedBy { it.date }
        stringResource(id = R.string.difficulty) -> routines.sortedBy { it.difficulty }
        else -> routines // Default to no sorting if option is not recognized
    }
}