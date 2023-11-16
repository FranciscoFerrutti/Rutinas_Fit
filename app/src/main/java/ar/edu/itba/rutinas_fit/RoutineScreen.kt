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
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.RowScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight

//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
                .clip(RoundedCornerShape(16.dp))

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
               modifier = Modifier.fillMaxHeight().fillMaxWidth()
           ) {
           item {
               Text(
               text = "Nombre Rutina",
               color = Color.White,
               fontFamily = FontFamily.SansSerif,
               fontSize = 18.sp,
               modifier = Modifier
                   .padding(Dp(5f))
           ) }
           item {
               Text(
               text = "Dificultad",
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
fun RoutineDesc(navController: NavController) {

}


@Composable
fun RoutineScreen(navController : NavController) {
    Box (modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            item {
                Box (
                modifier = Modifier.height(300.dp).width(400.dp).background(Color.White
                )
            ) {

            }
        }
        item {
            LazyColumn(
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .padding(vertical = Dp(5f)),
                verticalArrangement = Arrangement.spacedBy(Dp(25f))
            ) {
                items(10) { i ->
                    ExerciseElem(navController,
                        modifier = Modifier
                            .padding(vertical = Dp(120f))
                            .background(color = Color.Transparent)
                            .fillMaxWidth(),
                        imageResourceId = R.drawable.gymimg
                    )
                }
            }}
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
