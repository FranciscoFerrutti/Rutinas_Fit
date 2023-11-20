package ar.edu.itba.rutinas_fit.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.itba.rutinas_fit.R
import ar.edu.itba.rutinas_fit.data.model.Routine
import ar.edu.itba.rutinas_fit.data.model.Sport
import ar.edu.itba.rutinas_fit.ui.sport.SportViewModel
import ar.edu.itba.rutinas_fit.ui.sport.canAddSport
import ar.edu.itba.rutinas_fit.ui.sport.canDeleteSport
import ar.edu.itba.rutinas_fit.ui.sport.canGetAllSports
import ar.edu.itba.rutinas_fit.ui.sport.canGetCurrentSport
import ar.edu.itba.rutinas_fit.ui.sport.canGetCurrentUser
import ar.edu.itba.rutinas_fit.ui.sport.canModifySport
import ar.edu.itba.rutinas_fit.ui.routine.RoutineViewModel
import ar.edu.itba.rutinas_fit.ui.routine.canAddRoutine
import ar.edu.itba.rutinas_fit.ui.theme.Rutinas_FitTheme
import ar.edu.itba.rutinas_fit.ui.user.UserViewModel
import ar.edu.itba.rutinas_fit.ui.user.canGetCurrentUser
import ar.edu.itba.rutinas_fit.util.getViewModelFactory
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Rutinas_FitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun ActionButton(
    @StringRes resId: Int,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        enabled = enabled,
        onClick = onClick,
    ) {
        Text(
            text = stringResource(resId),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun MainScreen(
    userViewModel: UserViewModel = viewModel(factory = getViewModelFactory()),
    sportViewModel: SportViewModel = viewModel(factory = getViewModelFactory()),
    routineViewModel: RoutineViewModel = viewModel(factory = getViewModelFactory())
) {
    val uiState = userViewModel.uiState
    val routineUiState = routineViewModel.routineState
    val sportUiState = sportViewModel.sportState
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        if (!uiState.isAuthenticated) {
            ActionButton(
                resId = R.string.login,
                onClick = {
                    userViewModel.login("alanna40", "alanna40")
                })
        } else {
            ActionButton(
                resId = R.string.logout,
                onClick = {
                    userViewModel.logout()
                })
        }

        ActionButton(
            resId = R.string.get_current_user,
            enabled = uiState.canGetCurrentUser,
            onClick = {
                userViewModel.getCurrentUser()
            })
        ActionButton(
            resId = R.string.get_all_sports,
            enabled = uiState.canGetAllSports,
            onClick = {
                sportViewModel.getSports()
            })
        ActionButton(
            resId = R.string.get_current_user_routines,
            enabled = uiState.canGetCurrentUser,
            onClick = {
                routineViewModel.getCurrentUserRoutines()
            })
        ActionButton(
            resId = R.string.get_current_sport,
            enabled = uiState.canGetCurrentSport,
            onClick = {
                val currentSport = uiState.currentSport!!
                sportViewModel.getSport(currentSport.id!!)
            })
        ActionButton(
            resId = R.string.add_sport,
            enabled = uiState.canAddSport,
            onClick = {
                val random = Random.nextInt(0, 100)
                val sport = Sport(name = "Sport $random", detail = "Detail $random")
                sportViewModel.addOrModifySport(sport)
            })
        ActionButton(
            resId = R.string.modify_sport,
            enabled = uiState.canModifySport,
            onClick = {
                val random = Random.nextInt(0, 100)
                val currentSport = uiState.currentSport!!
                val sport = Sport(currentSport.id, currentSport.name, detail = "Detail $random")
                sportViewModel.addOrModifySport(sport)
            })
        ActionButton(
            resId = R.string.delete_sport,
            enabled = uiState.canDeleteSport,
            onClick = {
                val currentSport = uiState.currentSport!!
                sportViewModel.deleteSport(currentSport.id!!)
            })
        ActionButton(
            resId = R.string.add_routine,
            enabled = uiState.canAddRoutine,
            onClick = {
                val random = Random.nextInt(0, 150)
                val routine = Routine(name = "Routine $random", detail = "Detail $random", isPublic = true, difficulty = "rookie")
                routineViewModel.addOrModifyRoutine(routine)
            })
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val currentUserData = uiState.currentUser?.let {
                "Current User: ${it.firstName} ${it.lastName} (${it.email})"
            }
            Text(
                text = currentUserData ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                fontSize = 18.sp
            )
            val currentSportData = sportUiState.currentSport?.let {
                "Current Sport: (${it.id}) ${it.name} - ${it.detail}"
            }
            Text(
                text = currentSportData ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                fontSize = 18.sp
            )
            Text(
                text = "Total Sports: ${sportUiState.sports?.size ?: "unknown"}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                fontSize = 18.sp
            )
            Text(
                text = "Total Routines: ${routineUiState.routines?.size ?: "unknown"}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                fontSize = 18.sp
            )
            if (uiState.error != null) {
                Text(
                    text = "${uiState.error.code} - ${uiState.error.message}",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    fontSize = 18.sp
                )
            }
        }
    }
}