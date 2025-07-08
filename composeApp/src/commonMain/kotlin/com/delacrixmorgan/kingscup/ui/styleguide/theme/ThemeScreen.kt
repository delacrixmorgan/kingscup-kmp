package com.delacrixmorgan.kingscup.ui.styleguide.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bedtime
import androidx.compose.material.icons.rounded.SettingsSuggest
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.delacrixmorgan.kingscup.data.preferences.model.ThemePreference
import com.delacrixmorgan.kingscup.theme.AppTheme
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.styleGuide_themeDark
import kingscup.composeapp.generated.resources.styleGuide_themeLabel
import kingscup.composeapp.generated.resources.styleGuide_themeLight
import kingscup.composeapp.generated.resources.styleGuide_themeSystem
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ThemeScreen(
    modifier: Modifier = Modifier,
    state: ThemeUiState,
    onAction: (ThemeAction) -> Unit,
    haptic: HapticFeedback = LocalHapticFeedback.current,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp),
    ) {
        ListItem(headlineContent = { Text(stringResource(Res.string.styleGuide_themeLabel)) })
        Row(
            Modifier
                .background(ListItemDefaults.containerColor)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
        ) {
            ThemePreference.entries.forEach { theme ->
                val (icon, name) = when (theme) {
                    ThemePreference.System -> Icons.Rounded.SettingsSuggest to stringResource(Res.string.styleGuide_themeSystem)
                    ThemePreference.Light -> Icons.Rounded.WbSunny to stringResource(Res.string.styleGuide_themeLight)
                    ThemePreference.Dark -> Icons.Rounded.Bedtime to stringResource(Res.string.styleGuide_themeDark)
                }
                ToggleButton(
                    modifier = Modifier.weight(1F).semantics { role = Role.RadioButton },
                    checked = state.selectedTheme == theme,
                    onCheckedChange = {
                        haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
                        onAction(ThemeAction.OnThemeSelected(theme))
                    },
                    shapes = when (theme.ordinal) {
                        0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                        ThemePreference.entries.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                        else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                    }
                ) {
                    Icon(imageVector = icon, contentDescription = name)
                    Spacer(Modifier.size(ToggleButtonDefaults.IconSpacing))
                    Text(name)
                }
            }
        }
    }
}

@Preview
@Composable
private fun Private() {
    AppTheme {
        ThemeScreen(
            state = ThemeUiState(),
            onAction = {}
        )
    }
}