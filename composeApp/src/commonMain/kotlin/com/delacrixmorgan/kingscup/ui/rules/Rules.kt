package com.delacrixmorgan.kingscup.ui.rules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.card.model.Card
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.component.AppBar
import com.delacrixmorgan.kingscup.ui.component.AppScaffold
import com.delacrixmorgan.kingscup.ui.component.NavigationBackIcon
import com.delacrixmorgan.kingscup.ui.extensions.defaultRule
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Composable
fun RulesRoot(viewModel: RulesViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    RulesScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun RulesScreen(
    state: RulesUiState,
    onAction: (RulesAction) -> Unit,
) {
    AppScaffold(
        topBar = { scrollBehavior ->
            AppBar(
                title = "Rules",
                navigationIcon = { NavigationBackIcon { onAction(RulesAction.OnBackClicked) } },
                scrollBehavior = scrollBehavior,
            )
        },
        content = { innerPadding ->
            LazyVerticalGrid(
                modifier = Modifier.padding(innerPadding),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.cards, key = { it.uuid }) { card ->
                    Card(
                        modifier = Modifier.aspectRatio(63f / 88f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    ) {
                        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                            Text(
                                text = card.rank.toString(),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                textAlign = TextAlign.Center
                            )
                            Box(
                                modifier = Modifier
                                    .width(36.dp)
                                    .aspectRatio(1f)
                                    .background(MaterialTheme.colorScheme.surfaceContainer, shape = CircleShape)
                                    .align(Alignment.CenterHorizontally),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(card.rule.emoji),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(Modifier.height(12.dp))
                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = stringResource(card.rule.label),
                                style = MaterialTheme.typography.bodyLargeEmphasized,
                                color = MaterialTheme.colorScheme.onPrimaryFixed,
                                textAlign = TextAlign.Center

                            )
                            Box(Modifier.weight(1F), contentAlignment = Alignment.Center) {
                                BasicText(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(card.rule.description),
                                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimaryFixed, textAlign = TextAlign.Center),
                                    autoSize = TextAutoSize.StepBased(maxFontSize = MaterialTheme.typography.bodySmall.fontSize)
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
private fun Preview() {
    AppTheme {
        RulesScreen(
            state = RulesUiState(
                cards = Card.RankType.entries.mapNotNull { rank ->
                    rank.defaultRule()?.let { Card(uuid = Uuid.random().toString(), suit = Card.SuitType.Spade, rank = rank, rule = it) }
                }
            ),
            onAction = {}
        )
    }
}