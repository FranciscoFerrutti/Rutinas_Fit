package ar.edu.itba.rutinas_fit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.rutinas_fit.navigation.navigateToRoutine
import ar.edu.itba.rutinas_fit.ui.theme.Rutinas_FitTheme
import components.NavBar

@Composable
fun MainHeader(modifier: Modifier) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dp(70f))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF000000), // Black with alpha
                        Color(0xFF008000)  // Green
                    ),
                    start = Offset(x = 0f, y = 0f),
                    end = Offset.Infinite,
                )
            )
            .zIndex(1f)
    ){
        Text(
            text = "Mis Rutinas",
            color = Color.White,
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(Dp(10f))
                .align(Alignment.BottomStart)
                .zIndex(2f)
        )
    }
}


@Composable
fun CardElem(navController : NavController, modifier : Modifier, imageResourceId: Int) {
    val backgroundImage: Painter = painterResource(id = imageResourceId)
    Rutinas_FitTheme {

        Box(
            modifier = Modifier
                .background(color = Color.Transparent)
                .height(height = Dp(150f))
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    navigateToRoutine(navController)
                },

        ) {
            Box(
                modifier = Modifier
                    .background(Color(0.10f, 0.10f, 0.10f, alpha = 0.7f))
                    .fillMaxHeight(0.35f)
                    .fillMaxWidth()
                    .zIndex(2f)

            ) {
                Text(
                    text = "Nombre Rutina",
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(horizontal = Dp(8f), vertical = 5.dp)
                )
                Text(
                    text = "❚ Dificultad",
                    color = Color(174, 255, 0),
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = Dp(10f), vertical = Dp(4f))
                )

            }
            Image(
                painter = backgroundImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f),
                contentScale = ContentScale.Crop,
            )
        }
    }
}


@Composable
fun HomePageScreen(navController: NavController) {
    Box() {
        MainHeader(
            modifier = Modifier.background(Color.Transparent)
        )


        Box(
            modifier = Modifier.padding(vertical = Dp(70f), horizontal = Dp(10F)),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = Dp(5f)),
                verticalArrangement = Arrangement.spacedBy(Dp(25f))
            ) {
                items(10) { i ->
                    CardElem(navController,
                        modifier = Modifier
                            .padding(vertical = Dp(120f))
                            .background(color = Color.Transparent),
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