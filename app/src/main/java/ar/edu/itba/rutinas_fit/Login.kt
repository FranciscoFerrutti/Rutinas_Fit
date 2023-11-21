package ar.edu.itba.rutinas_fit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.itba.rutinas_fit.navigation.navigateToHome
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LoginRegisterScreenPreview() {
    // Create a NavController instance for preview
    val navController = rememberNavController()
    LoginRegisterScreen(navController)
}

@Composable
fun LoginRegisterScreen(navController: NavController) {
    var isLoginMode by remember { mutableStateOf(true) }
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

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

                if (password != confirmPassword) {
                    errorMessage = "Las contraseñas no coinciden"
                    return@Button
                }


                // Continue with registration or login based on the mode
                if (isLoginMode) {
                    handleLogin(navController, username, password)
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
    // Perform registration API call or other relevant actions
    // If successful, navigate to the home screen
    navigateToHome(navController)
}
