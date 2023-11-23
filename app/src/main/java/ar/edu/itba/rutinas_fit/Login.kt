package ar.edu.itba.rutinas_fit


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
//import ar.edu.itba.rutinas_fit.navigation.navigateToHome
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.itba.rutinas_fit.classes.MainViewModel
import ar.edu.itba.rutinas_fit.navigation.Screen
import ar.edu.itba.rutinas_fit.navigation.Screen.Exercise.route
import ar.edu.itba.rutinas_fit.util.ViewModelFactory
import ar.edu.itba.rutinas_fit.util.getViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LoginRegisterScreenPreview() {
    // Create a NavController instance for preview
    val navController = rememberNavController()
    LoginRegisterScreen(navController)
}
@Composable
fun loginContinueButton(navController: NavController,viewModel : MainViewModel, route: String, username : String, password : String, id: Int) : String{
    val scope = rememberCoroutineScope()
    var error by remember{mutableStateOf("")}

    Button(
        onClick = {
            scope.launch{
                viewModel.login(username,password).invokeOnCompletion {
                    if(viewModel.uiState.isAuthenticated) {
                        viewModel.getCurrentUser().invokeOnCompletion {
                            viewModel.getRoutines().invokeOnCompletion {
                                if(id == -1) {
                                    navController.navigate(route) {
                                        popUpTo("welcome_screen") { inclusive = true }
                                    }
                                } else{
                                    navController.navigate("share_screen?id=${id}"){
                                        popUpTo("welcome_screen")
                                    }
                                }
                            }
                        }
                    } else{
                        error = viewModel.uiState.message!!
                    }
                }
            } },
        modifier = Modifier
            .width(250.dp)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Green
        ),
        shape = RoundedCornerShape(40.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 5.dp, pressedElevation = 8.dp)
    ) {
        Text(stringResource(R.string.login), fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Justify)
    }
    if(error != ""){
        return stringResource(id = R.string.invalid_field)
    }
    return error
}

@Composable
fun LoginRegisterScreen(navController: NavController, mainViewModel: MainViewModel = viewModel(factory = getViewModelFactory())) {

    var isLoginMode by remember { mutableStateOf(true) }
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(245, 245, 245))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (isLoginMode) "Rutinas Fit" else "Rutinas Fit",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))
        if (!isLoginMode) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (!isLoginMode) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Mail") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = dateOfBirth,
                onValueChange = { dateOfBirth = it },
                label = { Text("Fecha de nacimiento") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        if (!isLoginMode) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirmar Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                // Input validation checks
                if (email.isNotBlank() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    errorMessage = "Correo inválido"
                    return@Button
                }

                if (password != confirmPassword && !isLoginMode) {
                    errorMessage = "Las contraseñas no coinciden"
                    return@Button
                }


                // Continue with registration or login based on the mode
                if (isLoginMode) {
                    scope.launch {
                        mainViewModel.login(username, password, navController)
                    }
                    // navigateToHome(navController)
                } else {
                    handleRegister(
                        navController = navController,
                        name = name,
                        username = username,
                        email = email,
                        dateOfBirth = dateOfBirth,
                        password = password,
                    )
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isLoginMode) "Inicia sesión" else "Regístrate")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { isLoginMode = !isLoginMode },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isLoginMode) "¿No tienes una cuenta? Regístrate" else "¿Ya tienes una cuenta? Inicia sesión")
        }

        // Display error message
        if (errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

private fun handleLogin(navController: NavController, username: String, password: String) {
    // TODO: Implement login logic here
    navigateToHome(navController)
}

private fun handleRegister(
    navController: NavController,
    name: String,
    username: String,
    email: String,
    dateOfBirth: String,
    password: String,
) {
    // TODO: Implement register logic here
}