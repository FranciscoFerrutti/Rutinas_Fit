package ar.edu.itba.rutinas_fit
import android.media.Image
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner


import androidx.activity.compose.setContent
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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.RowScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight

//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.rutinas_fit.data.model.Cycle
import ar.edu.itba.rutinas_fit.data.model.CycleExercise
import ar.edu.itba.rutinas_fit.data.model.Exercise
import ar.edu.itba.rutinas_fit.data.model.FullCycle

//import ar.edu.itba.rutinas_fit.navigation.navigateToRest
//import ar.edu.itba.rutinas_fit.navigation.navigateToRoutine


import components.NavBar
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.Date


@Composable
fun ExerciseScreen(navController : NavController, routineId : String, routineCycles : List<Cycle>, cycleExercisesList : List<FullCycle>) {
    val imageResource = R.drawable.gymimg
    var clockSize = 300.dp
    var paddin = 10.dp
    var imWid = 0.7f
    var imHei = 0.3f
    var mode by remember { mutableStateOf(false) }

//    val routineCycles = listOf(
//        Cycle(id = 101, name = "Cycle 1", detail = "Details 1", type = "Type A", order = 1, repetitions = 1, metadata = null),
//        Cycle(id = 101, name = "Cycle 2", detail = "Details 2", type = "Type B", order = 2, repetitions = 1, metadata = null),
//        Cycle(id = 101, name = "Cycle 3", detail = "Details 3", type = "Type C", order = 3, repetitions = 1, metadata = null)
//        // Add more objects as needed
//    )
//
//    val cycleExercisesList = listOf(
//        // Cycle 1
//        listOf(
//            CycleExercise(order = 1, duration = 0, repetitions = 8, exercise = Exercise(id = 1, name = "Exercise A1", detail = "Details A1", type = "Type A", date = Date(), metadata = null)),
//            CycleExercise(order = 2, duration = 0, repetitions = 10, exercise = Exercise(id = 2, name = "Exercise A2", detail = "Details A2", type = "Type A", date = Date(), metadata = null)),
//            CycleExercise(order = 3, duration = 15, repetitions = 0, exercise = Exercise(id = 3, name = "Exercise A3", detail = "Details A3", type = "Type A", date = Date(), metadata = null))
//        ),
//
//        // Cycle 2
//        listOf(
//            CycleExercise(order = 1, duration = 0, repetitions = 8, exercise = Exercise(id = 4, name = "Exercise B1", detail = "Details B1", type = "Type B", date = Date(), metadata = null)),
//            CycleExercise(order = 2, duration = 0, repetitions = 10, exercise = Exercise(id = 5, name = "Exercise B2", detail = "Details B2", type = "Type B", date = Date(), metadata = null)),
//            CycleExercise(order = 3, duration = 15, repetitions = 0, exercise = Exercise(id = 6, name = "Exercise B3", detail = "Details B3", type = "Type B", date = Date(), metadata = null))
//        ),
//
//        // Cycle 3
//        listOf(
//            CycleExercise(order = 1, duration = 0, repetitions = 8, exercise = Exercise(id = 7, name = "Exercise C1", detail = "Details C1", type = "Type C", date = Date(), metadata = null)),
//            CycleExercise(order = 2, duration = 0, repetitions = 10, exercise = Exercise(id = 8, name = "Exercise C2", detail = "Details C2", type = "Type C", date = Date(), metadata = null)),
//            CycleExercise(order = 3, duration = 15, repetitions = 0, exercise = Exercise(id = 9, name = "Exercise C3", detail = "Details C3", type = "Type C", date = Date(), metadata = null))
//        )
//    )

    var currentExerciseIndex by remember { mutableStateOf(0) }
    var currentCycleIndex by remember { mutableStateOf(0) }
    var totalCount by remember { mutableStateOf(cycleExercisesList[currentCycleIndex].exercises.size) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(200.dp)
            .padding(top = paddin)
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (!isDeviceInLandscape(LocalContext.current)){
            Column(
                modifier = Modifier.align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                        Image(
//                            painter = painterResource(id = imageResource),
//                            contentDescription = null,
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier
//                                .fillMaxWidth(imWid)
//                                .fillMaxHeight(imHei)
//                                .clip(RoundedCornerShape(16.dp))
////                    .align(Alignment.TopCenter)
//                        )
                if (cycleExercisesList[currentCycleIndex].exercises[currentExerciseIndex].duration == 0) {
                    Text(
                        text = cycleExercisesList[currentCycleIndex].exercises[currentExerciseIndex].exercise.name
                            ?: "N/A",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 48.sp,
                        modifier = Modifier
                            .padding(Dp(5f))
//                    .align(Alignment.Center)
                    )
                    Text(
                        text = cycleExercisesList[currentCycleIndex].exercises[currentExerciseIndex].exercise.detail,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
//                    modifier = Modifier
//                        .padding(Dp(5f))
//                    .align(Alignment.Center)
                    )
                    Box (modifier = Modifier
                        .fillMaxWidth()
                        .height(clockSize)){
                        Text(
                            text = cycleExercisesList[currentCycleIndex].exercises[currentExerciseIndex].repetitions.toString(),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 108.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(Dp(5f))
                                .align(Alignment.Center)
//                    .align(Alignment.Center)
                        )
                    }
                    Button(
                        onClick = { // aca avmos a tener un array o algo para ver cuantos ejs tiene el ciclo
                            if (currentExerciseIndex >= totalCount - 1){
                                if (currentCycleIndex < 2) {
                                    currentCycleIndex += 1
                                    currentExerciseIndex = 0

                                    totalCount = cycleExercisesList[currentCycleIndex].exercises.size
                                } else {
                                    navigateToRoutine(navController, routineId)
                                    // back to routine screen
                                }
                            } else {
                                currentExerciseIndex +=1
                            }
                        },
                        modifier = Modifier
                            .width(140.dp)
                            .height(50.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.next), color = MaterialTheme.colorScheme.onBackground,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 20.sp
                        )
                    }
                    if (mode){
                        if (currentCycleIndex < cycleExercisesList.size) {
                            CycleComp(cycleExercisesList[currentCycleIndex].exercises)
                        }
                    }
                    Box (modifier = Modifier.fillMaxWidth()){
                        Button(
                            onClick = { // aca avmos a tener un array o algo para ver cuantos ejs tiene el ciclo
                                mode = !mode
                            },
                            modifier = Modifier
                                .width(125.dp)
                                .height(40.dp)
                                .align(Alignment.Center)
                                .padding(top = 5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                text = stringResource(R.string.mode), color = MaterialTheme.colorScheme.onBackground,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 12.sp
                            )
                        }
                    }
                } else {
                    var countdownSeconds by remember { mutableStateOf(cycleExercisesList[currentCycleIndex].exercises[currentExerciseIndex].duration) }

                    var isCountdownRunning by remember { mutableStateOf(false) }
                    Text(
                        text = cycleExercisesList[currentCycleIndex].exercises[currentExerciseIndex].exercise!!.name,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 48.sp,
                        modifier = Modifier
                            .padding(Dp(5f))
//                    .align(Alignment.Center)
                    )
                    Text(
                        text = cycleExercisesList[currentCycleIndex].exercises[currentExerciseIndex].exercise!!.detail,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
//                    modifier = Modifier
//                        .padding(Dp(5f))
//                    .align(Alignment.Center)
                    )
                    Text(
                        text = stringResource(R.string.timerstart),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
//                    modifier = Modifier
//                        .padding(Dp(5f))
//                    .align(Alignment.Center)
                    )
                    Button(
                        onClick = {
                            isCountdownRunning = true
                            object :
                                CountDownTimer((countdownSeconds * 1000).toLong(), 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    countdownSeconds = (millisUntilFinished / 1000).toInt()
                                }

                                override fun onFinish() {
                                    countdownSeconds = 0
                                    isCountdownRunning = false
                                }
                            }.start()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .size(clockSize)
                            .padding(top = 10.dp)
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = CircleShape
                            )
                    ) {
                        Text(
                            text = countdownSeconds.toString(),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 108.sp,
                            modifier = Modifier
                                .padding(Dp(5f))
//                    .align(Alignment.Center)
                        )
                    }
                    Button(
                        onClick = { // aca avmos a tener un array o algo para ver cuantos ejs tiene el ciclo
                            if (currentExerciseIndex >= totalCount - 1){
                                if (currentCycleIndex < 2) {
                                    currentCycleIndex += 1
                                    currentExerciseIndex = 0
                                    totalCount = cycleExercisesList[currentCycleIndex].exercises.size
                                } else {
                                    navigateToRoutine(navController, routineId)
                                    // back to routine screen
                                }
                            } else {
                                currentExerciseIndex +=1
                            }
                        },
                        modifier = Modifier
                            .width(140.dp)
                            .height(60.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.next), color = MaterialTheme.colorScheme.onBackground,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 20.sp
                        )
                    }
                    if (mode){
                        CycleComp(cycleExercisesList[currentCycleIndex].exercises)
                    }

                }

            }
        } else {
            clockSize = 200.dp
            paddin = 5.dp
            imWid = 0.35f
            imHei = 0.7f
            Row(
                modifier = Modifier.align(Alignment.TopCenter),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box (modifier = Modifier.padding(start = 50.dp)){
//                        Image(
//                            painter = painterResource(id = imageResource),
//                            contentDescription = null,
//                            contentScale = ContentScale.Fit,
//                            modifier = Modifier
//                                .fillMaxWidth(imWid)
//                                .fillMaxHeight(imHei)
//                                .clip(RoundedCornerShape(16.dp))
////                    .align(Alignment.TopCenter)
//                        )
                }
                if (cycleExercisesList[currentCycleIndex].exercises[currentExerciseIndex].duration == 0) {
                    Column (modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 5.dp)) {
                        Text(
                            text = cycleExercisesList[currentCycleIndex].exercises[currentExerciseIndex].exercise?.name ?: "N/A",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 48.sp,
                            modifier = Modifier
                                .padding(Dp(5f))
                                .align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = cycleExercisesList[currentCycleIndex].exercises[currentExerciseIndex].exercise!!.detail,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center
//                    modifier = Modifier
//                        .padding(Dp(5f))
//                    .align(Alignment.Center)
                        )
                        Box (modifier = Modifier
                            .size(clockSize)
                            .align(Alignment.CenterHorizontally)){
                            Text(
                                text = cycleExercisesList[currentCycleIndex].exercises[currentExerciseIndex].repetitions.toString(),
                                color = MaterialTheme.colorScheme.onBackground,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 108.sp,
                                modifier = Modifier
                                    .padding(Dp(5f))

                            )}
                        Button(
                            onClick = { // aca avmos a tener un array o algo para ver cuantos ejs tiene el ciclo
                                if (currentExerciseIndex >= totalCount - 1){
                                    if (currentCycleIndex < 2) {
                                        currentCycleIndex += 1
                                        currentExerciseIndex = 0
                                        totalCount = cycleExercisesList[currentCycleIndex].exercises.size
                                    } else {
                                        navigateToRoutine(navController, routineId)
                                        // back to routine screen
                                    }
                                } else {
                                    currentExerciseIndex +=1
                                }
                            },
                            modifier = Modifier
                                .width(140.dp)
                                .height(50.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                text = stringResource(R.string.next), color = Color.White,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 20.sp
                            )
                        }
                        if (mode){
                            CycleComp(cycleExercisesList[currentCycleIndex].exercises)
                        }
                    }
                } else {
                    var countdownSeconds by remember { mutableStateOf(cycleExercisesList[currentCycleIndex].exercises[currentExerciseIndex].duration) }
                    var isCountdownRunning by remember { mutableStateOf(false) }
                    Column (modifier = Modifier.fillMaxSize()){
                        Text(
                            text = cycleExercisesList[currentCycleIndex].exercises[currentExerciseIndex].exercise?.name ?: "N/A",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 48.sp,
                            modifier = Modifier
                                .padding(Dp(5f))
//                    .align(Alignment.Center)
                        )
                        Text(
                            text = stringResource(R.string.timerstart),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(Dp(5f))
                        )
                        Button(
                            onClick = {
                                isCountdownRunning = true
                                object :
                                    CountDownTimer((countdownSeconds * 1000).toLong(), 1000) {
                                    override fun onTick(millisUntilFinished: Long) {
                                        countdownSeconds = (millisUntilFinished / 1000).toInt()
                                    }

                                    override fun onFinish() {
                                        countdownSeconds = 0
                                        isCountdownRunning = false
                                    }
                                }.start()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            modifier = Modifier
                                .size(clockSize)
                                .padding(top = 10.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color.White,
                                    shape = CircleShape
                                )
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = countdownSeconds.toString(),
                                color = MaterialTheme.colorScheme.onBackground,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 108.sp,
                                modifier = Modifier
                                    .padding(Dp(5f))
//                    .align(Alignment.Center)
                            )
                        }
                        Button(
                            onClick = { // aca avmos a tener un array o algo para ver cuantos ejs tiene el ciclo
                                if (currentExerciseIndex >= totalCount - 1){
                                    if (currentCycleIndex < 2) {
                                        currentCycleIndex += 1
                                        currentExerciseIndex = 1
                                        totalCount = cycleExercisesList[currentCycleIndex].exercises.size
                                    } else {
                                        navigateToRoutine(navController, routineId)
                                        // back to routine screen
                                    }
                                } else {
                                    currentExerciseIndex +=1
                                }
                            },
                            modifier = Modifier
                                .width(140.dp)
                                .height(60.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                text = stringResource(R.string.next), color = MaterialTheme.colorScheme.onBackground,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 20.sp
                            )
                        }
                        if (mode){
                            CycleComp(cycleExercisesList[currentCycleIndex].exercises)
                        }
                    }

                }
            }
        }
    }

}

@Composable
fun ExerciseScreen2(name : String, repetitions : Int, duration : Int ) {

}

