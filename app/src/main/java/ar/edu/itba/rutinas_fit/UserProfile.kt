package ar.edu.itba.rutinas_fit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import components.NavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var avatarUrl by remember { mutableStateOf("") }

    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        cursorColor = Color.White,
        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.White,
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.White
    )

    Box(
        modifier = Modifier
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
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    UserProfileImage(avatarUrl) { newAvatarUrl -> avatarUrl = newAvatarUrl }
                    Text(
                        text = "Cambiar avatar",
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 18.dp)
                            .padding(top = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        // TODO: Implementar lógica para eliminar cuenta
                    },
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    modifier = Modifier.padding(top = 32.dp)
                ) {
                    Text("Eliminar Cuenta", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("Nombre", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Apellido", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = gender,
                onValueChange = { gender = it },
                label = { Text("Género", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Teléfono", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        // TODO: Implementar lógica para guardar los cambios
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF004D40)),
                ) {
                    Text("Guardar Cambios", color = Color.White)
                }
                Button(
                    onClick = {
                        // TODO: Implementar lógica para cerrar sesión
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF388E3C))
                ) {
                    Text("Cerrar Sesión", color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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


@Composable
fun UserProfileImage(avatarUrl: String, onAvatarChange: (String) -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(150.dp)
            .border(2.dp, Color.White, CircleShape)
            .padding(16.dp)
            .clickable {
                // TODO: Implementar lógica para cambiar la imagen de perfil
            }
    ) {
        if (avatarUrl.isEmpty()) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Cambiar avatar",
                tint = Color.White,
                modifier = Modifier.size(118.dp)
            )
        } else {
            // Si avatarUrl es el nombre de un recurso drawable...
            val image = painterResource(id = R.drawable.gymimg)
            // TODO: revisar esto con la logico a implementar
            Image(
                painter = image,
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(128.dp) // Tamaño ajustado dentro del círculo
                    .clip(CircleShape)
            )
        }
    }
}