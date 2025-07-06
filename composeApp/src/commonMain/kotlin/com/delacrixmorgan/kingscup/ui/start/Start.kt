package com.delacrixmorgan.kingscup.ui.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Public
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.composables.core.ModalBottomSheetState
import com.composables.core.SheetDetent.Companion.FullyExpanded
import com.composables.core.SheetDetent.Companion.Hidden
import com.composables.core.rememberModalBottomSheetState
import com.delacrixmorgan.kingscup.data.localemanager.rememberLocaleHelper
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.component.BoxBackground
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.app_name
import kingscup.composeapp.generated.resources.img_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StartRoot(viewModel: StartViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    StartScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    state: StartUiState,
    onAction: (StartAction) -> Unit,
    uriHandler: UriHandler = LocalUriHandler.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val localeHelper = rememberLocaleHelper()
    BoxBackground {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.widthIn(max = 100.dp),
                painter = painterResource(Res.drawable.img_logo),
                contentDescription = null
            )
            Spacer(Modifier.height(36.dp))

            Text(
                stringResource(Res.string.app_name),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onPrimaryFixed
            )
            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SmallFloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    shape = CircleShape,
                    onClick = { onAction(StartAction.OnSupportClicked) },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Favorite,
                        contentDescription = "Support"
                    )
                }

                LargeFloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    shape = CircleShape,
                    onClick = { onAction(StartAction.OnSetupClicked) },
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = "Setup game"
                    )
                }

                SmallFloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    shape = CircleShape,
                    onClick = { onAction(StartAction.OnLocalisationClicked) },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Public,
                        contentDescription = "Change Language"
                    )
                }
            }
            Spacer(Modifier.height(220.dp))
        }
    }

    val sheetState: ModalBottomSheetState = rememberModalBottomSheetState(initialDetent = Hidden)
    LocaleBottomSheetSelector(
        sheetState = sheetState,
        state = state,
        onAction = onAction
    )

    LaunchedEffect(state.showLocalisationBottomSheet, lifecycleOwner) {
        sheetState.targetDetent = if (state.showLocalisationBottomSheet) FullyExpanded else Hidden
    }

    LaunchedEffect(state.openContactUs, lifecycleOwner) {
        if (state.openContactUs) {
            val email = "delacrixmorgan@gmail.com"
            val subject = "King's Cup - Translation Request"
            uriHandler.openUri("mailto:$email?subject=$subject")
            onAction(StartAction.OpenContactUs(open = false))
        }
    }

    LaunchedEffect(state.selectedLocale, lifecycleOwner) {
        localeHelper.setLanguage(state.selectedLocale.code)
    }

    LaunchedEffect(state.openAppSettings, lifecycleOwner) {
        if (state.openAppSettings) {
            localeHelper.openAppSettings()
            onAction(StartAction.OnLocalisationAppSettingsOpened)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        StartScreen(
            state = StartUiState(),
            onAction = {}
        )
    }
}