package ar.edu.itba.rutinas_fit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.itba.rutinas_fit.ui.theme.Rutinas_FitTheme
//import androidx.compose.foundation.layout.Side
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.RowScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight

//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.rutinas_fit.classes.MainViewModel
import ar.edu.itba.rutinas_fit.data.model.Cycle
import ar.edu.itba.rutinas_fit.data.model.CycleExercise
import ar.edu.itba.rutinas_fit.data.model.Exercise
import ar.edu.itba.rutinas_fit.data.model.FullCycle
import ar.edu.itba.rutinas_fit.data.model.Routine

import ar.edu.itba.rutinas_fit.navigation.Screen.Exercise.title
import ar.edu.itba.rutinas_fit.util.getViewModelFactory
//import ar.edu.itba.rutinas_fit.navigation.navigateToExercise
import components.NavBar
import java.util.Date


@SuppressLint("SuspiciousIndentation")
@Composable
fun ExerciseElem(modifier : Modifier, imageResourceId: Int, cycleExercise : CycleExercise) {
    var repStr = cycleExercise.repetitions.toString()
    if (cycleExercise.repetitions == 0){
        repStr = cycleExercise.duration.toString() + '"'
    }
    val backgroundImage: Painter = painterResource(id = imageResourceId)
        LazyRow(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .height(70.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                },

            ) {
            item {
                Image(
                    painter = backgroundImage,
                    contentDescription = null,
                    modifier = Modifier
                        .width(80.dp)
                        .fillMaxHeight()
                        .zIndex(1f),
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .zIndex(2f)

                ) {
                    LazyColumn (
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        item {
                            Text(
                                text = cycleExercise.exercise.name,
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(Dp(5f))
                            ) }
                        item {
                            Text(
                                text = repStr,
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(5.dp)
                            )
                        }
                    }

                }
            }
        }
}

@Composable
fun CycleComp(cycleExercises : List<CycleExercise>){

    LazyColumn(
        modifier = Modifier
            .height(435.dp)
            .fillMaxWidth()
            .padding(vertical = Dp(5f)),
        verticalArrangement = Arrangement.spacedBy(Dp(25f))
    ) {
        items(items = cycleExercises) { exercise ->
            ExerciseElem(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .fillMaxWidth(),
                imageResourceId = R.drawable.gymimg,
                exercise
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoutineScreen(navController : NavController, routineId : String, mainViewModel: MainViewModel = viewModel(factory = getViewModelFactory())) {

    val currentScreen = remember { mutableStateOf(1) }

        var currentCycle by remember { mutableStateOf(0) }
        var totalCycles by remember { mutableStateOf(3) }
        val pagerState = rememberPagerState { 3 }
        var flag by remember { mutableStateOf(false) }
        var flagStop by remember { mutableStateOf(true) }
        if (flagStop) {
            mainViewModel.getCurrentUser()
            mainViewModel.getRoutine(routineId.toInt())
            mainViewModel.getCycles(routineId.toInt())
            mainViewModel.getFullCyclesExercises(routineId.toInt())

            flagStop = false
        }
        var routine = mainViewModel.uiState.currentRoutine
        var cycles = mainViewModel.uiState.currentCycles
        var cycleExercisesUnordered = mainViewModel.uiState.currentWorkout
        // Assuming FullCycle has a default constructor
        var cycleExercises: MutableList<FullCycle> = MutableList(3) { FullCycle(cycleId = 1, exercises = listOf()) }




//    println(cycles[0].name)
//    val routineCycles = listOf(
//        Cycle(id = 101, name = "Cycle 1", detail = "Details 1", type = "Type A", order = 1, repetitions = 1, metadata=null),
//        Cycle(id = 101, name = "Cycle 2", detail = "Details 2", type = "Type B", order = 2, repetitions = 1, metadata=null),
//        Cycle(id = 101, name = "Cycle 3", detail = "Details 3", type = "Type C", order = 3, repetitions = 1, metadata=null)
//        // Add more objects as needed
//    )
        try {
            Log.wtf("wtf", cycles[0].name)
            Log.wtf("wtf", routine!!.name)
            Log.wtf("wtf", "attempting fullcycles")
            Log.wtf("wtf", cycleExercises[0].toString())
            flag = true

            cycleExercisesUnordered.forEach { cycle ->
                cycles.forEach { cycleInner ->
                    if (cycle.cycleId == cycleInner.id){
                        cycleExercises[cycleInner.order-1] = cycle
                    }
                }
            }
            Log.wtf("ENTIRE THING", cycleExercises.toString())
        } catch (e: Exception) {
            Log.e("er", e.toString())
        }

        if (currentScreen.value == 1) {
        if (flag) {
            var height = 0.3f
            var paddin = 50.dp
            if (isDeviceInLandscape(LocalContext.current)) {
                height = 0.38f
                paddin = 5.dp
            }
            Box(modifier = Modifier.fillMaxSize()) {
                Column() {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(
                                height
                            )
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.inverseSurface)
                    )
                    {
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .background(MaterialTheme.colorScheme.inverseSurface)
                                .fillMaxSize()
                                .padding(top = paddin),


                            ) {
                            Text(
                                text = routine!!.name,
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 26.sp,
                                modifier = Modifier
                                    .padding(top = Dp(8f))
                                    .align(Alignment.CenterHorizontally),
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = "@${routine.user!!.username}",
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(Dp(5f))
                                    .align(Alignment.CenterHorizontally),
                                textAlign = TextAlign.Center

                            )
                            Button(
                                onClick = {
                                    currentScreen.value = 2
                                },
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(50.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 5.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                            ) {
                                Text(
                                    text = stringResource(R.string.execute),
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 20.sp
                                )
                            }
                        }

                    }

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) { index ->
                        currentCycle = index
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(40.dp)
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.primary)
                                    .clip(RoundedCornerShape(48.dp))
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (currentCycle > 0) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .zIndex(3f)
                                                .size(34.dp),
                                            tint = MaterialTheme.colorScheme.inverseOnSurface
                                        )
                                    } else {
                                        Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .zIndex(3f)
                                                .size(44.dp)
                                                .clickable {
                                                    if (currentCycle > 0)
                                                        currentCycle -= 1
                                                },
                                            tint = Color.Transparent
                                        )
                                    }

                                    Text(
                                        text = cycles[currentCycle].name,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        fontFamily = FontFamily.SansSerif,
                                        fontSize = 22.sp,

                                        textAlign = TextAlign.Center
                                    )
                                    if (currentCycle < totalCycles - 1) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowForward,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .zIndex(3f)
                                                .size(34.dp),
                                            tint = MaterialTheme.colorScheme.inverseOnSurface
                                        )
                                    } else {
                                        Icon(
                                            imageVector = Icons.Default.ArrowForward,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .zIndex(3f)
                                                .size(44.dp)
                                                .clickable {
                                                    if (currentCycle < totalCycles - 1)
                                                        currentCycle += 1
                                                },
                                            tint = Color.Transparent
                                        )
                                    }
                                }
                            }
                            CycleComp(cycleExercises[currentCycle].exercises)
                        }
                    }

//            CycleComp(navController, currentCycle.toString())
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.14f)
                        .align(Alignment.BottomCenter)
                )
                {

                    NavBar(
                        navController,
                        modifier = Modifier
                            .background(Color.White)
                    )
                }
            }
        }
    } else {

            ExerciseScreen(
                navController = navController,
                routineId = routineId,
                routineCycles = cycles,
                cycleExercisesList = cycleExercises.toList()
            )
    }
}
