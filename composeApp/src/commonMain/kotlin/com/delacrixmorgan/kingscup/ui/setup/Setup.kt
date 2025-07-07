package com.delacrixmorgan.kingscup.ui.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bedtime
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.SettingsSuggest
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.preferences.model.ThemePreference
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.theme.appListItemColors
import com.delacrixmorgan.kingscup.ui.component.AppBar
import com.delacrixmorgan.kingscup.ui.component.AppScaffold
import com.delacrixmorgan.kingscup.ui.component.NavigationBackIcon
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.setup_buttonPlay
import kingscup.composeapp.generated.resources.setup_jokersDescription
import kingscup.composeapp.generated.resources.setup_jokersLabel
import kingscup.composeapp.generated.resources.setup_rulesDescription
import kingscup.composeapp.generated.resources.setup_rulesLabel
import kingscup.composeapp.generated.resources.setup_themeDark
import kingscup.composeapp.generated.resources.setup_themeLabel
import kingscup.composeapp.generated.resources.setup_themeLight
import kingscup.composeapp.generated.resources.setup_themeSystem
import kingscup.composeapp.generated.resources.setup_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SetupRoot(viewModel: SetupViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SetupScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SetupScreen(
    state: SetupUiState,
    onAction: (SetupAction) -> Unit,
) {
    AppScaffold(
        topBar = { scrollBehavior ->
            AppBar(
                title = stringResource(Res.string.setup_title),
                navigationIcon = { NavigationBackIcon { onAction(SetupAction.OnBackClicked) } },
                scrollBehavior = scrollBehavior,
            )
        },
        content = { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                ToggleSection(state, onAction)
                Spacer(Modifier.weight(1F))
                val size = ButtonDefaults.ExtraLargeContainerHeight
                Button(
                    modifier = Modifier
                        .heightIn(size)
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    contentPadding = ButtonDefaults.contentPaddingFor(size),
                    onClick = { onAction(SetupAction.OnStartClicked) }
                ) {
                    Icon(
                        modifier = Modifier.size(ButtonDefaults.iconSizeFor(size)),
                        contentDescription = null,
                        imageVector = Icons.Rounded.PlayArrow
                    )
                    Spacer(Modifier.size(ButtonDefaults.iconSpacingFor(size)))
                    Text(stringResource(Res.string.setup_buttonPlay), style = ButtonDefaults.textStyleFor(size))
                }
                Spacer(Modifier.height(32.dp))
            }
        }
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun ToggleSection(
    state: SetupUiState,
    onAction: (SetupAction) -> Unit,
    haptic: HapticFeedback = LocalHapticFeedback.current,
) {
    Column(
        Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        ListItem(
            modifier = Modifier.clickable {
                haptic.performHapticFeedback(if (!state.jokersEnabled) HapticFeedbackType.ToggleOn else HapticFeedbackType.ToggleOff)
                onAction(SetupAction.OnJokerSettingsToggled)
            },
            colors = appListItemColors(),
            headlineContent = { Text(stringResource(Res.string.setup_jokersLabel)) },
            supportingContent = { Text(stringResource(Res.string.setup_jokersDescription)) },
            trailingContent = {
                Switch(
                    checked = state.jokersEnabled,
                    onCheckedChange = {
                        haptic.performHapticFeedback(if (it) HapticFeedbackType.ToggleOn else HapticFeedbackType.ToggleOff)
                        onAction(SetupAction.OnJokerSettingsToggled)
                    }
                )
            }
        )

        ListItem(
            modifier = Modifier.clickable { onAction(SetupAction.OnRulesClicked) },
            colors = appListItemColors(),
            headlineContent = { Text(stringResource(Res.string.setup_rulesLabel)) },
            supportingContent = { Text(stringResource(Res.string.setup_rulesDescription)) },
            trailingContent = { Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = null) }
        )

        ListItem(
            colors = appListItemColors(),
            headlineContent = { Text(stringResource(Res.string.setup_themeLabel)) }
        )
        FlowRow(
            Modifier
                .background(appListItemColors().containerColor)
                .padding(16.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
            verticalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween)
        ) {
            ThemePreference.entries.forEach { theme ->
                val (icon, name) = when (theme) {
                    ThemePreference.System -> Icons.Rounded.SettingsSuggest to stringResource(Res.string.setup_themeSystem)
                    ThemePreference.Light -> Icons.Rounded.WbSunny to stringResource(Res.string.setup_themeLight)
                    ThemePreference.Dark -> Icons.Rounded.Bedtime to stringResource(Res.string.setup_themeDark)
                }
                ToggleButton(
                    modifier = Modifier.weight(1F).semantics { role = Role.RadioButton },
                    checked = state.selectedTheme == theme,
                    onCheckedChange = {
                        haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
                        onAction(SetupAction.OnThemeSelected(theme))
                    },
                    shapes = when (theme.ordinal) {
                        0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                        ThemePreference.entries.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                        else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                    }
                ) {
                    Icon(imageVector = icon, contentDescription = name)
                    Spacer(Modifier.size(ToggleButtonDefaults.IconSpacing))
                    Text(name, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        SetupScreen(
            state = SetupUiState(),
            onAction = {}
        )
    }
}