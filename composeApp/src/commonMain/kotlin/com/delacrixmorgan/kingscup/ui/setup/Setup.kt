package com.delacrixmorgan.kingscup.ui.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bedtime
import androidx.compose.material.icons.rounded.CheckCircleOutline
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.preferences.model.SkinPreference
import com.delacrixmorgan.kingscup.data.preferences.model.ThemePreference
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.theme.appListItemColors
import com.delacrixmorgan.kingscup.ui.component.AppBar
import com.delacrixmorgan.kingscup.ui.component.AppScaffold
import com.delacrixmorgan.kingscup.ui.component.NavigationBackIcon
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
                title = "Setup",
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
                        Icons.Rounded.PlayArrow,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(ButtonDefaults.iconSizeFor(size))
                    )
                    Spacer(Modifier.size(ButtonDefaults.iconSpacingFor(size)))
                    Text("Play", style = ButtonDefaults.textStyleFor(size))
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
            colors = appListItemColors(),
            headlineContent = { Text("Jokers") },
            supportingContent = { Text("Spice things up a bit") },
            trailingContent = {
                Switch(
                    checked = state.jokersEnabled,
                    onCheckedChange = {
                        haptic.performHapticFeedback(if (it) HapticFeedbackType.ToggleOn else HapticFeedbackType.ToggleOff)
                        onAction(SetupAction.OnJokerSettingsToggled(it))
                    }
                )
            }
        )

        ListItem(
            modifier = Modifier.clickable { onAction(SetupAction.OnRulesClicked) },
            colors = appListItemColors(),
            headlineContent = { Text("Rules") },
            supportingContent = { Text("Refresh your memory") },
            trailingContent = {
                Icon(
                    imageVector = Icons.Rounded.ChevronRight,
                    contentDescription = "Open rules screen",
                )
            }
        )

        ListItem(
            colors = appListItemColors(),
            headlineContent = { Text("Theme") }
        )
        Row(
            Modifier
                .background(appListItemColors().containerColor)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
        ) {
            ThemePreference.entries.forEach { theme ->
                val (icon, name) = when (theme) {
                    ThemePreference.System -> Icons.Rounded.SettingsSuggest to "System"
                    ThemePreference.Light -> Icons.Rounded.WbSunny to "Light"
                    ThemePreference.Dark -> Icons.Rounded.Bedtime to "Dark"
                }
                ToggleButton(
                    modifier = Modifier.weight(1F).semantics { role = Role.RadioButton },
                    checked = state.selectedTheme == theme,
                    onCheckedChange = { onAction(SetupAction.OnThemeSelected(theme)) },
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

        ListItem(
            colors = appListItemColors(),
            headlineContent = { Text("Skin") }
        )
        Row(
            Modifier
                .background(appListItemColors().containerColor)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SkinPreference.entries.forEach {
                Box(
                    Modifier
                        .weight(1F)
                        .aspectRatio(63F / 88F)
                        .background(it.getCardColor(), RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
                            onAction(SetupAction.OnSkinSelected(it))
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (state.selectedSkin?.name == it.name) {
                        Icon(
                            imageVector = Icons.Rounded.CheckCircleOutline,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            contentDescription = "${it.name} skin selected",
                        )
                    }
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