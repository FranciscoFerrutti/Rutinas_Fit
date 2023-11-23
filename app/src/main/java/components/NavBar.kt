package components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import ar.edu.itba.rutinas_fit.navigateToFavorite
import ar.edu.itba.rutinas_fit.navigateToHome
import ar.edu.itba.rutinas_fit.navigateToProfile
import ar.edu.itba.rutinas_fit.navigateToSearch
import ar.edu.itba.rutinas_fit.navigateToSettings

//import ar.edu.itba.rutinas_fit.navigation.navigateToFavorite
//import ar.edu.itba.rutinas_fit.navigation.navigateToHome
//import ar.edu.itba.rutinas_fit.navigation.navigateToProfile
//import ar.edu.itba.rutinas_fit.navigation.navigateToSearch
//import ar.edu.itba.rutinas_fit.navigation.navigateToSettings

//import ar.edu.itba.rutinas_fit.naviagteToSearch
//import ar.edu.itba.rutinas_fit.navigateToHome
//import ar.edu.itba.rutinas_fit.navigateToProfile
//import ar.edu.itba.rutinas_fit.navigateToRoutine

@Composable
fun NavBar(navController: NavController, modifier: Modifier) {
    Box (
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .border(1.dp, MaterialTheme.colorScheme.background)
            .zIndex(4f)

    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                MaterialTheme.colorScheme.inverseSurface
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
            verticalAlignment = Alignment.CenterVertically,
        ){
            item () {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    modifier = Modifier
                        .zIndex(3f)
                        .size(44.dp).clickable {
                            navigateToHome(navController)
                        },
                    tint = MaterialTheme.colorScheme.inverseOnSurface
                )

            }
            item {
                Icon(imageVector = Icons.Default.Search, contentDescription = null, modifier = Modifier
                    .zIndex(3f)
                    .size(44.dp).clickable {
                        navigateToSearch(navController)
                    } ,  tint = MaterialTheme.colorScheme.inverseOnSurface)
            }
            item {
                Icon(imageVector = Icons.Default.Star, contentDescription = null, modifier = Modifier
                    .zIndex(3f)
                    .size(44.dp).clickable {
                        navigateToFavorite(navController)
                    } , tint = MaterialTheme.colorScheme.inverseOnSurface)

            }
            item {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null, modifier = Modifier
                    .zIndex(3f)
                    .size(44.dp).clickable {
                        navigateToSettings(navController)
                    } , tint = MaterialTheme.colorScheme.inverseOnSurface)

            }

            item {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier
                    .zIndex(3f)
                    .size(44.dp).clickable {
                        navigateToProfile(navController)
                    } , tint = MaterialTheme.colorScheme.inverseOnSurface)
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
    }
}

