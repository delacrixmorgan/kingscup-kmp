package com.delacrixmorgan.kingscup.ui.support

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Feedback
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Policy
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.rateUsStoreLink
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.theme.appListItemColors
import com.delacrixmorgan.kingscup.ui.component.AppBar
import com.delacrixmorgan.kingscup.ui.component.AppScaffold
import com.delacrixmorgan.kingscup.ui.component.NavigationBackIcon
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.support_appInfoLabel
import kingscup.composeapp.generated.resources.support_privacyPolicyLabel
import kingscup.composeapp.generated.resources.support_rateUsLabel
import kingscup.composeapp.generated.resources.support_sendFeedbackLabel
import kingscup.composeapp.generated.resources.support_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SupportRoot(viewModel: SupportViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SupportScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportScreen(
    state: SupportUiState,
    onAction: (SupportAction) -> Unit,
    uriHandler: UriHandler = LocalUriHandler.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    AppScaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        topBar = { scrollBehavior ->
            AppBar(
                title = stringResource(Res.string.support_title),
                navigationIcon = { NavigationBackIcon { onAction(SupportAction.OnBackClicked) } },
                scrollBehavior = scrollBehavior,
            )
        },
        content = { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                PortfolioSection(state)
                Spacer(Modifier.height(16.dp))

                DetailsSection(onAction)
                Spacer(Modifier.weight(1F))

                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = state.version,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White
                )
            }
        }
    )

    LaunchedEffect(state, lifecycleOwner) {
        if (state.openPrivacyPolicy) {
            uriHandler.openUri("https://github.com/delacrixmorgan/kingscup-kmp/blob/main/PRIVACY_POLICY.md")
            onAction(SupportAction.OpenPrivacyPolicy(open = false))
        }
        if (state.openSendFeedback) {
            val email = "delacrixmorgan@gmail.com"
            val subject = "King's Cup - App Feedback"
            uriHandler.openUri("mailto:$email?subject=$subject")
            onAction(SupportAction.OpenSendFeedback(open = false))
        }
        if (state.openRateUs) {
            uriHandler.openUri(rateUsStoreLink)
            onAction(SupportAction.OpenRateUs(open = false))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PortfolioSection(
    state: SupportUiState,
    uriHandler: UriHandler = LocalUriHandler.current,
) {
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { state.portfolio.size },
        modifier = Modifier.width(412.dp),
        preferredItemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) { index ->
        val portfolio = state.portfolio[index]
        Box(
            modifier = Modifier
                .width(202.dp)
                .aspectRatio(63F / 88F)
                .maskClip(shape = MaterialTheme.shapes.extraLarge)
                .background(Color(portfolio.backgroundColor), shape = RoundedCornerShape(12.dp))
                .clickable { uriHandler.openUri(portfolio.url) },
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(52.dp),
                    painter = painterResource(portfolio.logo),
                    contentDescription = null
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = portfolio.name,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    modifier = Modifier.heightIn(min = (MaterialTheme.typography.bodyMedium.lineHeight * 2).value.dp),
                    text = portfolio.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun DetailsSection(
    onAction: (SupportAction) -> Unit,
) {
    Column(
        Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        ListItem(
            modifier = Modifier.clickable { onAction(SupportAction.OnAppInfoClicked) },
            colors = appListItemColors(),
            leadingContent = { Icon(imageVector = Icons.Rounded.Info, contentDescription = null) },
            headlineContent = { Text(stringResource(Res.string.support_appInfoLabel)) },
            trailingContent = {
                Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = null)
            }
        )
        ListItem(
            modifier = Modifier.clickable { onAction(SupportAction.OpenPrivacyPolicy(open = true)) },
            colors = appListItemColors(),
            leadingContent = { Icon(imageVector = Icons.Rounded.Policy, contentDescription = null) },
            headlineContent = { Text(stringResource(Res.string.support_privacyPolicyLabel)) },
            trailingContent = { Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = null) }
        )
        ListItem(
            modifier = Modifier.clickable { onAction(SupportAction.OpenSendFeedback(open = true)) },
            colors = appListItemColors(),
            leadingContent = { Icon(imageVector = Icons.Rounded.Feedback, contentDescription = null) },
            headlineContent = { Text(stringResource(Res.string.support_sendFeedbackLabel)) },
            trailingContent = { Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = null) }
        )
        ListItem(
            modifier = Modifier.clickable { onAction(SupportAction.OpenRateUs(open = true)) },
            colors = appListItemColors(),
            leadingContent = { Icon(imageVector = Icons.Rounded.ThumbUp, contentDescription = null) },
            headlineContent = { Text(stringResource(Res.string.support_rateUsLabel)) },
            trailingContent = { Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = null) }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        SupportScreen(
            state = SupportUiState(
                version = "2025.1 (88)"
            ),
            onAction = {}
        )
    }
}