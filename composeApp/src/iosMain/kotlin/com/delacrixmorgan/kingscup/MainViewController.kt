package com.delacrixmorgan.kingscup

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.delacrixmorgan.kingscup.data.preferences.PreferencesRepository
import com.delacrixmorgan.kingscup.data.preferences.model.SkinPreference
import com.delacrixmorgan.kingscup.data.preferences.model.ThemePreference
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.App
import kotlinx.coroutines.launch
import org.koin.core.component.get

fun MainViewController() = ComposeUIViewController {
    val preferencesRepository = KoinHelper.get<PreferencesRepository>()
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
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(LocalLifecycleOwner.current) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch { preferencesRepository.skinFlow.collect { skin.value = it } }
            launch { preferencesRepository.themeFlow.collect { theme.value = it } }
        }
    }
}