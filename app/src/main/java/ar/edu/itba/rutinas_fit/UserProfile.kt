package ar.edu.itba.rutinas_fit

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ar.edu.itba.rutinas_fit.classes.MainViewModel
import ar.edu.itba.rutinas_fit.data.model.Name
//import ar.edu.itba.rutinas_fit.navigation.navigateToLogin

import ar.edu.itba.rutinas_fit.util.getViewModelFactory
import coil.compose.rememberImagePainter
import components.NavBar
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(navController: NavController, mainViewModel: MainViewModel = viewModel(factory = getViewModelFactory())) {
    //var firstName by remember { mutableStateOf("") }
    // Get the firstName from the logged in user, by using the UserViewModel
    val uiState = mainViewModel.uiState
    var firstName by remember { mutableStateOf("") }
    var flag by remember { mutableStateOf(true) }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var avatarUrl by remember { mutableStateOf("") }
    if(flag && mainViewModel.uiState.isAuthenticated && firstName == "") {
        mainViewModel.getCurrentUser().invokeOnCompletion {
            firstName = uiState.currentUser?.firstName ?: ""
            lastName = uiState.currentUser?.lastName ?: ""
            email = uiState.currentUser?.email ?: ""
            username = uiState.currentUser?.username ?: ""
            avatarUrl = uiState.currentUser?.avatarUrl ?: ""
        }
    } else {
        flag = false
    }

    /*uiState.currentUser?.let {
    it.avatarUrl
} ?: "No user logged in"*/
    var saveChangesEnabled by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        cursorColor = MaterialTheme.colorScheme.onBackground,
        focusedBorderColor = MaterialTheme.colorScheme.secondary,
        unfocusedBorderColor = MaterialTheme.colorScheme.surface,
        focusedLabelColor = MaterialTheme.colorScheme.inverseOnSurface,
        unfocusedLabelColor = MaterialTheme.colorScheme.inverseOnSurface,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        UserProfileImage(avatarUrl) { newAvatarUrl -> avatarUrl = newAvatarUrl }
                        Text(
                            text = stringResource(id = R.string.select_image),
                            color = MaterialTheme.colorScheme.background,
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .padding(top = 6.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))


            }

            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = username,
                onValueChange = {
                    if(it.length < 50){
                        username = it
                    }
                },
                label = { Text(stringResource(R.string.username), color = MaterialTheme.colorScheme.inverseOnSurface) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,

                )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = firstName,
                onValueChange = {
                    if(it.length < 50){
                        firstName = it
                    }
                },
                label = { Text(stringResource(R.string.firstname), color = MaterialTheme.colorScheme.inverseOnSurface) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,

                )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = lastName,
                onValueChange = {
                    if(it.length < 50){
                        lastName = it
                    }
                },
                label = { Text(stringResource(R.string.lastname), color = MaterialTheme.colorScheme.inverseOnSurface) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,

                )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value= email,
                onValueChange = {
                    if (it.length < 50) {
                        email = it
                    }
                },
                label = { Text(stringResource(R.string.email), color = MaterialTheme.colorScheme.inverseOnSurface) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Button(
                    enabled = saveChangesEnabled,
                    onClick = {
                        scope.launch {
                            saveChangesEnabled = false
                            mainViewModel.modifyUser(Name(username, firstName, lastName, email, avatarUrl)).invokeOnCompletion {
                                mainViewModel.getCurrentUser().invokeOnCompletion{
                                    saveChangesEnabled = true
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(stringResource(id = R.string.save_changes), color = MaterialTheme.colorScheme.onBackground)
                }
                Button(
                    onClick = {
                        mainViewModel.logout()
                        //if (!mainViewModel.uiState.isAuthenticated) {
                        navigateToLogin(navController)
                        //}
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(stringResource(R.string.sign_out), color = MaterialTheme.colorScheme.onBackground)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))


        }
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
    // Get the context
    val context = LocalContext.current

    // Create an ActivityResultLauncher to handle the image picker result
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            // Handle the selected image URI
            uri?.let {
                // Convert the URI to a string and pass it to the callback
                onAvatarChange(it.toString())
            }
        }
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(150.dp)
            .border(2.dp, MaterialTheme.colorScheme.inverseOnSurface, CircleShape)
            .padding(16.dp)
            .clickable {
                // Launch the image picker when the box is clicked
                imagePickerLauncher.launch("image/*")
            }
    ) {
        if (avatarUrl.isEmpty()) {
            // Display the default account circle icon
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = stringResource(R.string.select_image),
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(118.dp)
            )
        } else {
            // Display the selected image
            Image(
                painter = rememberImagePainter(avatarUrl),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(128.dp) // Adjusted size within the circle
                    .clip(CircleShape)
            )
        }
    }
}