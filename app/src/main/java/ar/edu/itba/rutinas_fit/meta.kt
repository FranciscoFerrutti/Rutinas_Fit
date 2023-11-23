import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
//import coil.compose.rememberAsyncImagePainter


data class ImageData(val resourceId: Int, val title: String, val subtitle: String)


@Composable
fun GoalSelectionScreen(navController: NavController, imageList: List<ImageData>) {
    var currentImageTitle by remember { mutableStateOf("") }
    val screenHeight = LocalContext.current.resources.displayMetrics.heightPixels.dp / LocalDensity.current.density
    val halfScreenHeight = screenHeight / 2
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Text(
            text = "¿Cuál es tu meta?",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 24.sp
        )
        Text(
            text = "Esto nos ayudará a elegir el mejor plan para vos",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(imageList) { index, imageData ->
                ImageCard(imageData, halfScreenHeight) { title ->
                    currentImageTitle = title
                }
                if (index == 0) { // Initially set to the first image title
                    currentImageTitle = imageData.title
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text("Confirmar", color = MaterialTheme.colorScheme.onBackground)
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}
@Composable
fun ImageCard(imageData: ImageData, height: Dp, onImageSelected: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(height)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageData.resourceId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        ) {
            Text(
                text = imageData.title,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp
            )
            Text(
                text = imageData.subtitle,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        }
        // Update the current image title when the image is clicked
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onImageSelected(imageData.title)
                }
        )
    }
}