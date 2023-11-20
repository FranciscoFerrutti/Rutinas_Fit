package ar.edu.itba.rutinas_fit
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.rutinas_fit.navigation.navigateToRest
import components.NavBar
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun ExerciseScreen(navController : NavController) {
    val imageResource = R.drawable.gymimg
    Rutinas_FitTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
                .padding(top = 10.dp)
        ) {
            Column (modifier = Modifier.align(Alignment.TopCenter), horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.3f)
                        .clip(RoundedCornerShape(16.dp))
//                    .align(Alignment.TopCenter)
                )
                if (true) {
                    Text(
                        text = "Biceps",
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 48.sp,
                        modifier = Modifier
                            .padding(Dp(5f))
//                    .align(Alignment.Center)
                    )
                    Text(
                        text = "12",
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 108.sp,
                        modifier = Modifier
                            .padding(Dp(5f))
//                    .align(Alignment.Center)
                    )
                    Button(onClick = {
                        navigateToRest(navController)
                    },
                        modifier = Modifier
                            .width(140.dp)
                            .height(50.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 5.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(64, 117, 60))
                    ) {
                        Text(
                            text = "Siguiente", color = Color.White,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 20.sp
                        )
                    }
                } else {
                        var countdownSeconds by remember { mutableStateOf(12) }
                        var isCountdownRunning by remember { mutableStateOf(false) }
                        Text(
                        text = "Biceps",
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 48.sp,
                        modifier = Modifier
                            .padding(Dp(5f))
//                    .align(Alignment.Center)
                        )
                        Text(
                            text = "Apretá para comenzar la cuenta del ejercicio",
                            color = Color.White,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center
//                    modifier = Modifier
//                        .padding(Dp(5f))
//                    .align(Alignment.Center)
                        )
                        Button(onClick = {
                            isCountdownRunning = true
                            object : CountDownTimer((countdownSeconds * 1000).toLong(), 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    countdownSeconds = (millisUntilFinished / 1000).toInt()
                                }

                                override fun onFinish() {
                                    countdownSeconds = 0
                                    isCountdownRunning = false
                                }
                            }.start() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            modifier = Modifier.size(300.dp).padding(top=10.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color.White,
                                    shape = CircleShape
                                )){
                            Text(
                                text = countdownSeconds.toString(),
                                color = Color.White,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 108.sp,
                                modifier = Modifier
                                    .padding(Dp(5f))
//                    .align(Alignment.Center)
                            )
                        }
                        Button(
                            onClick = {
                                navigateToRest(navController)
                            },
                            modifier = Modifier
                                .width(140.dp)
                                .height(60.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(64, 117, 60))
                        ) {
                            Text(
                                text = "Siguiente", color = Color.White,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 20.sp
                            )
                        }

                }
            }
        }

    }

}