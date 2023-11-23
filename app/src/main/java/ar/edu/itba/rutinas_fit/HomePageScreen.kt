package ar.edu.itba.rutinas_fit

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import ar.edu.itba.rutinas_fit.data.model.*
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.rutinas_fit.classes.MainViewModel
import ar.edu.itba.rutinas_fit.data.model.Routine

import ar.edu.itba.rutinas_fit.navigation.Screen
import ar.edu.itba.rutinas_fit.navigation.navigateToRoutine
import ar.edu.itba.rutinas_fit.ui.theme.Rutinas_FitTheme
import ar.edu.itba.rutinas_fit.util.getViewModelFactory
import components.NavBar
import kotlinx.coroutines.selects.select
import java.util.Date
import java.util.Locale

@Composable
fun MainHeader(modifier: Modifier, onOptionSelected: (String) -> Unit  ) {
    var expanded by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var order by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dp(70f))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.primary
                    ),
                    start = Offset(x = 0f, y = 0f),
                    end = Offset.Infinite,
                )
            )
            .zIndex(1f)
    ){
        Row (modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterStart), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
            Text(
                text = stringResource(id = R.string.myroutines),
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = FontFamily.SansSerif,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(Dp(10f))
                    .zIndex(2f)
            )
            Box () {
                Row (modifier = Modifier.clickable { expanded = true },verticalAlignment = Alignment.CenterVertically, horizontalArrangement =  Arrangement.SpaceEvenly){
                    Text(

                        text = stringResource(id = R.string.orderby),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 24.sp,
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier
                            .zIndex(3f)
                            .size(44.dp),
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.clip(shape = RoundedCornerShape(15.dp)),
                ) {
                    val dateString = stringResource(id = R.string.date)
                    DropdownMenuItem(
                        onClick = { onOptionSelected(dateString); title = dateString; expanded = false },
                        text = { Text(text = dateString) })

                    val difficultyString = stringResource(id = R.string.difficulty)
                    DropdownMenuItem(
                        onClick = { onOptionSelected(difficultyString); title = difficultyString; expanded = false },
                        text = { Text(text = difficultyString) })

                }
            }
        }
    }
}

@Composable
fun CardElem(navController: NavController, modifier: Modifier, imageResourceId: Int, routine: Routine, favInitialStatus: Boolean,
             mainViewModel: MainViewModel = viewModel(factory = getViewModelFactory())) {
    val backgroundImage: Painter = painterResource(id = imageResourceId)
    var rating by remember { mutableStateOf(0) }
    var isFavorite = favInitialStatus

    Box(
            modifier = Modifier
                .background(color = Color.Transparent)
                .height(height = Dp(150f))
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    navigateToRoutine(navController, routine.id.toString())
                },

            ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxHeight(0.35f)
                    .fillMaxWidth()
                    .zIndex(2f)
            ) {
                Text(
                    text = routine.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(horizontal = Dp(8f), vertical = 5.dp)
                )
                Text(
                    text = routine.difficulty?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }
                        ?: "",
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = Dp(10f), vertical = Dp(4f))
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = Dp(10f), top = Dp(4f))
                ) {
                    // Favorite star
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Dp(8f), vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        // Share icon
                        if(routine.isPublic!!) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        // TODO: Share routine
                                    }
                            )
                        }

                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    // TODO: Share routine
                                }
                        )

                        // Favorite star
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = null,
                            tint = if (isFavorite) Color.Red else Color.Gray,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    // Toggle the favorite status
                                    isFavorite = !isFavorite
                                    if(isFavorite) {
                                        mainViewModel.addToFavourites(routine.id)
                                    } else {
                                        mainViewModel.deleteFromFavourites(routine.id)
                                    }
                                }
                        )
                    }
                }
            }
            Image(
                painter = backgroundImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = Dp(10f), bottom = Dp(4f))
            ) {
                // Rating stars
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = if (index < rating) Color.Yellow else Color.Gray,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                // Set the rating to the clicked star index + 1
                                rating = index + 1
                                // TODO: Make API call to update exercise rating
                                // Example: api.updateExerciseRating(routine.id, rating)
                            }
                    )
                }
            }
    }
}


@Composable
fun Routines(navController: NavController, routines : List<Routine>, selectedOption : String, mainViewModel: MainViewModel = viewModel(factory = getViewModelFactory())) {
    var routinesSorted = getSortedRoutines(routines, selectedOption)
    Box(
        modifier = Modifier.padding(vertical = Dp(70f), horizontal = Dp(10F)),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = Dp(5f)),
            verticalArrangement = Arrangement.spacedBy(Dp(25f))
        ) {
            items(routinesSorted) { routine ->
                CardElem(
                    navController,
                    modifier = Modifier
                        .padding(vertical = Dp(120f))
                        .background(color = Color.Transparent),
                    imageResourceId = R.drawable.gymimg,
                    routine = routine,
                    favInitialStatus = mainViewModel.isFavourite(routine.id)
                )
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomePageScreen(navController: NavController, mainViewModel: MainViewModel = viewModel(factory = getViewModelFactory())) {
    var selectedOption by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var flag by remember { mutableStateOf(true) }
    if(flag) {
        mainViewModel.getCurrentUser()
        mainViewModel.getRoutines()
        mainViewModel.getFavourites()
        flag = false
    }
    Box() {
        MainHeader(
            modifier = Modifier.background(Color.Transparent),
            onOptionSelected = { selectedOption = it }
        )
        val routines = mainViewModel.uiState.routines
            listOf(
            Routine(id= 1, name = "Routine 1", detail = "Details", date = Date(), isPublic = true, difficulty = "Hard", category= null, score=2, metadata = null, user = null),
            Routine(id= 1, name = "Routine 2", detail = "Details", date = Date(), isPublic = true, difficulty = "Medium", category= null, score=2, metadata = null, user = null),
            Routine(id= 1, name = "Routine 3", detail = "Details", date = Date(), isPublic = true, difficulty = "Easy", category= null, score=2, metadata = null, user = null)
        )
        Routines(navController,routines, selectedOption)

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