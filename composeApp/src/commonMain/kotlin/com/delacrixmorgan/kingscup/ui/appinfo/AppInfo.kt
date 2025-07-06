package com.delacrixmorgan.kingscup.ui.appinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.theme.appListItemColors
import com.delacrixmorgan.kingscup.ui.component.AppBar
import com.delacrixmorgan.kingscup.ui.component.AppScaffold
import com.delacrixmorgan.kingscup.ui.component.NavigationBackIcon
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AppInfoRoot(viewModel: AppInfoViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    AppInfoScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppInfoScreen(
    state: AppInfoUiState,
    onAction: (AppInfoAction) -> Unit,
    uriHandler: UriHandler = LocalUriHandler.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    AppScaffold(
        topBar = { scrollBehavior ->
            AppBar(
                title = "App Info",
                navigationIcon = { NavigationBackIcon { onAction(AppInfoAction.OnBackClicked) } },
                scrollBehavior = scrollBehavior,
            )
        },
        content = { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                Column(
                    Modifier
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerLow)
                ) {
                    ListItem(
                        modifier = Modifier.clickable { onAction(AppInfoAction.OpenDeveloperPage(open = true)) },
                        colors = appListItemColors(),
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Rounded.Person,
                                contentDescription = null,
                            )
                        },
                        headlineContent = { Text("Developer") },
                        supportingContent = { Text("Delacrix Morgan") },
                        trailingContent = {
                            Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = null)
                        }
                    )
                    ListItem(
                        modifier = Modifier.clickable { onAction(AppInfoAction.OpenSourceCode(open = true)) },
                        colors = appListItemColors(),
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Rounded.Code,
                                contentDescription = null,
                            )
                        },
                        headlineContent = { Text("Source Code") },
                        supportingContent = { Text("GitHub") },
                        trailingContent = {
                            Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = null)
                        }
                    )
                    ListItem(
                        modifier = Modifier.clickable { onAction(AppInfoAction.OnStyleGuideClicked) },
                        colors = appListItemColors(),
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Rounded.Palette,
                                contentDescription = null,
                            )
                        },
                        headlineContent = { Text("Style Guide") },
                        supportingContent = { Text("Pixels and Hexes") },
                        trailingContent = {
                            Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = null)
                        }
                    )
                }
            }
        }
    )
    LaunchedEffect(state.openDeveloperPage, lifecycleOwner) {
        if (state.openDeveloperPage) {
            uriHandler.openUri("https://github.com/delacrixmorgan")
            onAction(AppInfoAction.OpenDeveloperPage(open = false))
        }
    }
    LaunchedEffect(state.openSourceCode, lifecycleOwner) {
        if (state.openSourceCode) {
            uriHandler.openUri("https://github.com/delacrixmorgan/kingscup-kmp")
            onAction(AppInfoAction.OpenSourceCode(open = false))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        AppInfoScreen(
            state = AppInfoUiState(),
            onAction = {}
        )
    }
}