package ar.edu.itba.rutinas_fit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ar.edu.itba.rutinas_fit.classes.MainViewModel
import ar.edu.itba.rutinas_fit.data.model.Review
import ar.edu.itba.rutinas_fit.util.getViewModelFactory
import kotlinx.coroutines.launch


@Composable
fun ReviewScreen(navController: NavController, mainViewModel: MainViewModel = viewModel(factory = getViewModelFactory())){
    var rating by remember { mutableIntStateOf(0) }
    var flag by remember { mutableStateOf(true) }
    var review by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val routine = mainViewModel.uiState.currentRoutine
    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF000000),
                    Color(0xFF007f00)
                ),
                start = Offset(x = 0f, y = 0f),
                end = Offset.Infinite
            )
        )){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)){
            // Here we need a big text field for the user to write the review
            // There must be also a 5 star rating system
            // And a button to submit the review
            OutlinedTextField(value = "", onValueChange = { review = it }, label = { stringResource(id = R.string.review) },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = androidx.compose.ui.Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(12.dp))
            Row(){
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
            Button(onClick = {
                scope.launch {
                    mainViewModel.reviewRoutine(
                        Review((rating * 2), review),
                        routine?.id?: 0
                    )
                }
            }) {
                Text(text = stringResource(id = R.string.submit_review))
            }
        }
    }
}