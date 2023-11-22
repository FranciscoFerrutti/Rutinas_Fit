package ar.edu.itba.rutinas_fit.data.model

data class FullCycle(
    var cycleId : Int,
    var exercises : List<CycleExercise> =listOf()
)