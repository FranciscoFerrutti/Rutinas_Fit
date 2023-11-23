package ar.edu.itba.rutinas_fit.ui.theme

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class ThemeViewModel : ViewModel() {
    var isDarkTheme = mutableStateOf(true)
        private set

    fun initializeTheme(isSystemInDark: Boolean) {
        isDarkTheme.value = isSystemInDark
    }

    fun switchTheme() {
        isDarkTheme.value = !isDarkTheme.value
    }
}