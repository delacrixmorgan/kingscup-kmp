package com.delacrixmorgan.kingscup.ui.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.composables.core.DragIndication
import com.composables.core.ModalBottomSheet
import com.composables.core.Scrim
import com.composables.core.Sheet
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
                color = MaterialTheme.colorScheme.onPrimary
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
            // TODO (Banner - Translation Request)
        }
    }

    val sheetState = rememberModalBottomSheetState(initialDetent = Hidden)
    ModalBottomSheet(
        state = sheetState,
        onDismiss = { onAction(StartAction.OnLocalisationBottomSheetDismissed) },
        content = {
            Scrim()
            Sheet(
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    )
                    .padding(WindowInsets.navigationBars.only(WindowInsetsSides.Vertical).asPaddingValues()),
            ) {
                Column(Modifier.fillMaxWidth()) {
                    DragIndication(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 10.dp)
                            .background(MaterialTheme.colorScheme.onSurfaceVariant, RoundedCornerShape(9999.dp))
                            .width(40.dp)
                            .height(4.dp),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                        text = "Select Locale",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    HorizontalMultiBrowseCarousel(
                        state = rememberCarouselState { state.locale.size },
                        modifier = Modifier.width(412.dp),
                        preferredItemWidth = 186.dp,
                        itemSpacing = 8.dp,
                        contentPadding = PaddingValues(horizontal = 16.dp),
                    ) { index ->
                        val localePreference = state.locale[index]
                        Box(
                            modifier = Modifier
                                .width(202.dp)
                                .aspectRatio(63F / 88F)
                                .maskClip(shape = MaterialTheme.shapes.extraLarge)
                                .background(MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(12.dp))
                                .clickable { onAction(StartAction.OnLocalisationChanged(localePreference)) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = localePreference.emoji,
                                style = MaterialTheme.typography.displayLarge
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    )

    LaunchedEffect(state.showLocalisationBottomSheet, lifecycleOwner) {
        sheetState.targetDetent = if (state.showLocalisationBottomSheet) FullyExpanded else Hidden
    }

    LaunchedEffect(state.selectedLocale) {
        localeHelper.setLanguage(state.selectedLocale.code)
    }

    LaunchedEffect(state.openAppSettings) {
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