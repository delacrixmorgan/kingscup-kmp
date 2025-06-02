package com.delacrixmorgan.kingscup.ui.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.preferences.model.SkinPreference
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.theme.color.BoraColorPreference
import com.delacrixmorgan.kingscup.theme.color.EmeraldColorPreference
import com.delacrixmorgan.kingscup.theme.color.MadderColorPreference
import com.delacrixmorgan.kingscup.theme.color.SandColorPreference
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SetupRoot(viewModel: SetupViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SetupScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@Composable
fun SetupScreen(
    state: SetupUiState,
    onAction: (SetupAction) -> Unit,
    haptic: HapticFeedback = LocalHapticFeedback.current,
) {
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer).padding(WindowInsets.systemBars.asPaddingValues())) {
        FilledIconButton(
            modifier = Modifier.padding(16.dp).size(64.dp).align(Alignment.Start),
            onClick = { onAction(SetupAction.OnBackClicked) }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = "Go back",
            )
        }

        ListItem(
            headlineContent = { Text("Jokers") },
            supportingContent = { Text("Just to spice things up a bit") },
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
            headlineContent = { Text("Rules") },
            supportingContent = { Text("Refresh your memory or customise them") },
            trailingContent = {
                Icon(
                    imageVector = Icons.Rounded.ChevronRight,
                    contentDescription = "Open rules screen",
                )
            }
        )

        ListItem(
            modifier = Modifier.clickable { onAction(SetupAction.OnRulesClicked) },
            headlineContent = { Text("Theme") },
        )

        // TODO (Segmented Button)

        ListItem(headlineContent = { Text("Skin") })
        Row(
            Modifier
                .background(ListItemDefaults.containerColor)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SkinPreference.entries.forEach {
                val backgroundColor = when (it) {
                    SkinPreference.Bora -> BoraColorPreference.onPrimaryContainerLight
                    SkinPreference.Emerald -> EmeraldColorPreference.onPrimaryContainerLight
                    SkinPreference.Madder -> MadderColorPreference.onPrimaryContainerLight
                    SkinPreference.Sand -> SandColorPreference.onPrimaryContainerLight
                }
                Box(
                    Modifier
                        .weight(1F)
                        .aspectRatio(63F / 88F)
                        .background(backgroundColor, RoundedCornerShape(12.dp))
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
                            tint = MaterialTheme.colorScheme.primaryContainer,
                            contentDescription = "${it.name} skin selected",
                        )
                    }
                }
            }
        }

        Spacer(Modifier.weight(1F))

        LargeFloatingActionButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { onAction(SetupAction.OnStartClicked) },
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Rounded.PlayArrow,
                contentDescription = "Setup game"
            )
        }

        Spacer(Modifier.height(32.dp))
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