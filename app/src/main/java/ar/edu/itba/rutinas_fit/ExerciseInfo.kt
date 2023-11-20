import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.rutinas_fit.R

data class ExerciseStep(val title: String, val description: String)

@Composable
fun ExerciseDetailsScreen(
    exerciseName: String,
    difficulty: String,
    description: String,
    steps: List<ExerciseStep>
) {
    val imageResource = R.drawable.gymimg  // Replace with your image resource

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
            .padding(16.dp)
    ) {
        // Image
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Exercise Name
        Text(
            text = exerciseName,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        // Difficulty
        Text(
            text = difficulty,
            color = Color.Gray,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Description
        Text(
            text = "Descripción",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            color = Color.Gray,
            fontSize = 16.sp,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .clickable {
                    // TODO: Implement the logic to expand the text
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // "Cómo hacerlo"
        Text(
            text = "Cómo hacerlo",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        // Exercise Steps
        LazyColumn {
            items(steps) { step ->
                ExerciseStepItem(step)
            }
        }
    }
}

@Composable
fun ExerciseStepItem(step: ExerciseStep) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        StepIndicator()
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = step.title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = step.description,
                color = Color.Gray,
                fontSize = 16.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .clickable {
                        // TODO: Implement the logic to expand the text
                    }
            )
        }
    }
}

@Composable
fun StepIndicator() {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(brush = Brush.radialGradient(listOf(Color.Green, Color(0xFF1A1A1A)), radius = 24f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseDetailsScreenPreview() {
    val exerciseName = "Ejercicio de Gimnasio"
    val difficulty = "Intermedio"
    val description = "Este es un ejercicio de gimnasio para mantenerte en forma y saludable. Asegúrate de seguir las instrucciones cuidadosamente para obtener los mejores resultados."
    val steps = listOf(
        ExerciseStep("Paso 1", "Realiza este paso con cuidado."),
        ExerciseStep("Paso 2", "Continúa con el siguiente paso."),
        ExerciseStep("Paso 3", "Sigue las instrucciones detalladamente."),
        ExerciseStep("Paso 4", "Completa el ejercicio con una postura correcta.")
    )

    ExerciseDetailsScreen(exerciseName, difficulty, description, steps)
}
