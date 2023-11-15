package ar.edu.itba.rutinas_fit

import android.os.Bundle
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.itba.rutinas_fit.ui.theme.Rutinas_FitTheme
//import androidx.compose.foundation.layout.Side
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight

//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.colorspace.Rgb
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Rutinas_FitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(10,10,10)
                ) {
                    StartPageBody()
                }
            }
        }
    }
}

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
fun NavBar(modifier: Modifier) {
    Box (
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color(1f,1f,1f,0.1f))
            .border(1.dp, Color(1f,1f,1f,0.1f))
            .zIndex(4f)

    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                Color(20,20,20)
            )
            .zIndex(1f),
    ){
//        Text(
//            text = "Mis Rutinas",
//            color = Color.White,
//            fontFamily = FontFamily.SansSerif,
//            fontSize = 30.sp,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .padding(Dp(10f))
//                .align(Alignment.BottomStart)
//                .zIndex(2f)
//        )
        LazyRow(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            item () {
                Icon(imageVector = Icons.Default.Home, contentDescription = null, modifier = Modifier.zIndex(3f).size(44.dp), tint = Color.White)
            }
            item {
                Icon(imageVector = Icons.Default.Search, contentDescription = null, modifier = Modifier.zIndex(3f).size(44.dp) ,  tint = Color.White)
            }
        item {
            Icon(imageVector = Icons.Default.List, contentDescription = null, modifier = Modifier.zIndex(3f).size(44.dp) , tint = Color.White)

        }

            item {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.zIndex(3f).size(44.dp) , tint = Color.White)
            }

//
//                Button(
//                    onClick = {
//                        // Handle button click
//                    },
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(0.1f, 0.1f, 0.1f, alpha = 0.95f),  // Set the text color
//                        contentColor = Color(134, 196, 0)
//                    ),
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .fillMaxWidth()
//                ) {
//                    Text(
//                        text="Ver rutina",
//                        fontSize = 16.sp,
//                        fontFamily = FontFamily.SansSerif,
//                    )
//                }
//            }
    }
}}


@Composable
fun CardElem(modifier : Modifier, imageResourceId: Int) {
    val backgroundImage: Painter = painterResource(id = imageResourceId)
    Rutinas_FitTheme {

    Box(
        modifier = Modifier
            .background(color = Color.Transparent)
            .height(height = Dp(200F))
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))

    ) {
       Box(
           modifier = Modifier
               .background(Color(0.10f, 0.10f, 0.10f, alpha = 0.5f))
               .fillMaxHeight(0.25f)
               .fillMaxWidth()
               .zIndex(2f)

       ) {
           Text(
               text = "Nombre Rutina",
               color = Color.White,
               fontFamily = FontFamily.SansSerif,
               fontSize = 18.sp,
               modifier = Modifier
                   .padding(Dp(5f))
           )
           Text(
               text = "❚ Dificultad",
               color = Color(174, 255, 0),
               fontFamily = FontFamily.SansSerif,
               fontSize = 14.sp,
               modifier = Modifier
                   .align(Alignment.BottomStart)
                   .padding(horizontal = Dp(8f), vertical = Dp(2f))
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
fun StartPageBody() {
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
                        CardElem(
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

                NavBar(
                    modifier = Modifier
                        .background(Color.White)
                )
            }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Rutinas_FitTheme {
        StartPageBody()
    }
}