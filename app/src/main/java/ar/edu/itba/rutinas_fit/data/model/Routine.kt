package ar.edu.itba.rutinas_fit.data.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ar.edu.itba.rutinas_fit.R
import ar.edu.itba.rutinas_fit.data.network.model.NetworkRoutine

data class Routine(
    var id: Int? = null,
    var name: String,
    var detail: String,
    var date: Int? = null,
    var isPublic: Boolean,
    var difficulty: String
    //var category: Category, // I think we do not use category
    // do we need metadata?
){
    fun asNetworkModel(): NetworkRoutine {
        return NetworkRoutine(
            id = id,
            name = name,
            detail = detail,
            date = date,
            isPublic = isPublic,
            difficulty = difficulty
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
