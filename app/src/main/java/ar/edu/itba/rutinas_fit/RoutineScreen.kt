package ar.edu.itba.rutinas_fit

import android.os.Bundle
import androidx.activity.ComponentActivity
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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.RowScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight

//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import ar.edu.itba.rutinas_fit.navigation.navigateToExercise
import components.NavBar

@Composable
fun ExerciseElem(navController: NavController, modifier : Modifier, imageResourceId: Int) {
    val backgroundImage: Painter = painterResource(id = imageResourceId)
    Rutinas_FitTheme {
        LazyRow(
            modifier = Modifier
                .background(Color(0.10f, 0.10f, 0.10f, alpha = 0.5f))
                .height(70.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)).clickable {
                    navigateToExercise(navController)
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
               text = "Biceps",
               color = Color.White,
               fontFamily = FontFamily.SansSerif,
               fontSize = 18.sp,
               modifier = Modifier
                   .padding(Dp(5f))
           ) }
           item {
               Text(
               text = "12x",
               color = Color(174, 255, 0),
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
}



@Composable
fun RoutineScreen(navController : NavController) {
    Box (modifier = Modifier.fillMaxSize()) {
        Column() {
                Box (
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .background(Color(10, 10, 10)))
                {

                    Column (
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(Color(0.10f, 0.10f, 0.10f, alpha = 0.5f))
                            .fillMaxSize()
                            .padding(top = 50.dp),


                    ){
                        Text(
                            text = "Nombre Rutina",
                            color = Color.White,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 26.sp,
                            modifier = Modifier
                                .padding(top = Dp(8f))
                                .align(Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "11 Ejercicios - 3 Ciclos",
                            color = Color.White,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(Dp(5f))
                                .align(Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center

                        )
                        Button(onClick = { },modifier = Modifier.width(140.dp).height(50.dp).align(Alignment.CenterHorizontally).padding(top=5.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(64, 117, 60))){
                            Text(text = "Ejecutar", color = Color.White,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 20.sp)
                        }
                    }

                }
            Box (
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .background(Color(30, 30, 30))
                    .clip(RoundedCornerShape(48.dp))
            ) {


               Row(modifier = Modifier.fillMaxSize(),horizontalArrangement = Arrangement.SpaceAround,verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier
                        .zIndex(3f)
                        .size(44.dp) ,  tint = Color.White)

                Text(
                    text = "Ciclo de entrada en calor",
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 22.sp,

                    textAlign = TextAlign.Center
                )
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier
                    .zIndex(3f)
                    .size(44.dp) ,  tint = Color.White)
            }
            }

            LazyColumn(
                modifier = Modifier
                    .height(435.dp)
                    .fillMaxWidth()
                    .padding(vertical = Dp(5f)),
                verticalArrangement = Arrangement.spacedBy(Dp(25f))
            ) {
                items(7) { i ->
                    ExerciseElem(navController,
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .fillMaxWidth(),
                        imageResourceId = R.drawable.gymimg
                    )
                }
            }
        }
    Box (
        modifier = Modifier
            .fillMaxHeight(0.14f)
            .align(Alignment.BottomCenter)
    )
    {

        NavBar(navController,
            modifier = Modifier
                .background(Color.White)
        )
    }
    }
}
