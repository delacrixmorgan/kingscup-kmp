package com.delacrixmorgan.kingscup

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.delacrixmorgan.kingscup.data.preferences.PreferencesRepository
import com.delacrixmorgan.kingscup.data.preferences.model.SkinPreference
import com.delacrixmorgan.kingscup.data.preferences.model.ThemePreference
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.App
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {

    private val preferencesRepository: PreferencesRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val skin = remember { mutableStateOf(SkinPreference.Default) }
            val theme = remember { mutableStateOf(ThemePreference.Default) }

            AppTheme(skin.value, theme.value) {
                Scaffold {
                    val insetModifier = Modifier
                        .windowInsetsPadding(WindowInsets.displayCutout)
                        .consumeWindowInsets(it)
                    App(insetModifier)
                }
            }
            val view = LocalView.current
            SideEffect {
                val window = (view.context as ComponentActivity).window
                val isDarkTheme = when (theme.value) {
                    ThemePreference.System -> isSystemInDarkTheme()
                    ThemePreference.Light -> false
                    ThemePreference.Dark -> true
                }
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme
            }
            val lifecycleOwner = LocalLifecycleOwner.current
            LaunchedEffect(LocalLifecycleOwner.current) {
                lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                    launch { preferencesRepository.skinFlow.collect { skin.value = it } }
                    launch { preferencesRepository.themeFlow.collect { theme.value = it } }
                }
            }
        }
    }

    private fun isSystemInDarkTheme(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}