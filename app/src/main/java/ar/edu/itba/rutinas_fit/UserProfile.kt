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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ar.edu.itba.rutinas_fit.classes.MainViewModel
//import ar.edu.itba.rutinas_fit.navigation.navigateToLogin

import ar.edu.itba.rutinas_fit.util.getViewModelFactory
import components.NavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(navController: NavController, mainViewModel: MainViewModel = viewModel(factory = getViewModelFactory())) {
    //var firstName by remember { mutableStateOf("") }
    // Get the firstName from the logged in user, by using the UserViewModel
    mainViewModel.getCurrentUser() // WE NEED to call getCurrentUser to fetch the data
    val uiState = mainViewModel.uiState
    var firstName = uiState.currentUser?.firstName ?: "No user logged in"
    var lastName = uiState.currentUser?.lastName ?: "No user logged in"
    var email = uiState.currentUser?.email ?: "No user logged in"
    var username = uiState.currentUser?.username ?: "No user logged in"
    var avatarUrl = uiState.currentUser?.avatarUrl ?: "No user logged in" /*uiState.currentUser?.let {
        it.avatarUrl
    } ?: "No user logged in"*/

    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        cursorColor = MaterialTheme.colorScheme.onBackground,
        focusedBorderColor = MaterialTheme.colorScheme.onBackground,
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground,
        focusedLabelColor = MaterialTheme.colorScheme.onBackground,
        unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.primary
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
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .padding(top = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Column (
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            // TODO: Implementar lógica para guardar los cambios
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                    ) {
                        Text("Guardar Cambios", color = MaterialTheme.colorScheme.background)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            mainViewModel.logout()
                            //if (!mainViewModel.uiState.isAuthenticated) {
                            navigateToLogin(navController)
                            //}
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onBackground)
                    ) {
                        Text("Cerrar Sesión", color = MaterialTheme.colorScheme.background)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nombre de usuario", color = MaterialTheme.colorScheme.onBackground) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                readOnly = true,
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("Nombre", color = MaterialTheme.colorScheme.onBackground) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                readOnly = true,
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Apellido", color = MaterialTheme.colorScheme.onBackground) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                readOnly = true,
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = MaterialTheme.colorScheme.onBackground) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                readOnly = true,
            )
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
            .border(2.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
            .padding(16.dp)
            .clickable {
                // TODO: Implementar lógica para cambiar la imagen de perfil
            }
    ) {
        if (avatarUrl.isEmpty()) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Cambiar avatar",
                tint = MaterialTheme.colorScheme.onBackground,
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