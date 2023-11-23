package ar.edu.itba.rutinas_fit.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(46, 87, 0),
    secondary = Color(189, 255, 74),
    background = Color(30,30,30),
    onBackground = Color.White,
    surface = DarkGray,
    inverseOnSurface = Color(245,245,245),
    inverseSurface = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = Color(70, 120, 42),
    secondary = DarkGreen,
    background = Color(235,235,235),
    onBackground = Color(245,245,245),
    surface = LightGray,
    inverseOnSurface = Color(40,40,40),
    inverseSurface = Color(245,245,245),
)

@Composable
fun Rutinas_FitTheme(
    themeViewModel: ThemeViewModel,
    content: @Composable () -> Unit
) {
    val colorScheme = if (themeViewModel.isDarkTheme.value) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}