package com.delacrixmorgan.kingscup.ui.rules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.component.BoxBackground
import com.delacrixmorgan.kingscup.ui.component.NavigationBackIcon
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RulesRoot(viewModel: RulesViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    RulesScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@Composable
fun RulesScreen(
    state: RulesUiState,
    onAction: (RulesAction) -> Unit,
) {
    BoxBackground {
        Column(modifier = Modifier.padding(top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding())) {
            NavigationBackIcon { onAction(RulesAction.OnBackClicked) }

            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                state.cards.forEach { card ->
                    ListItem(
                        modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                        leadingContent = {
                            Box(
                                modifier = Modifier
                                    .width(36.dp)
                                    .aspectRatio(1f)
                                    .background(MaterialTheme.colorScheme.tertiaryContainer, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    card.rank.toString(),
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        headlineContent = {
                            Text(stringResource(card.rule.label))
                        },
                        supportingContent = {
                            Text(stringResource(card.rule.description))
                        }
                    )
                }
                Spacer(Modifier.height(32.dp))
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        RulesScreen(
            state = RulesUiState(),
            onAction = {}
        )
    }
}