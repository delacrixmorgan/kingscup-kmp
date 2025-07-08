package com.delacrixmorgan.kingscup.ui.rules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.card.model.Card
import com.delacrixmorgan.kingscup.data.card.model.Normal
import com.delacrixmorgan.kingscup.ui.extensions.defaultRule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class RulesViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RulesUiState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                hasLoadedInitialData = true
                loadData()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = RulesUiState()
        )

    @OptIn(ExperimentalUuidApi::class)
    private fun loadData() {
        Normal.King
        _state.update {
            it.copy(
                cards = Card.RankType.entries.mapNotNull { rank ->
                    rank.defaultRule()?.let { Card(uuid = Uuid.random().toString(), suit = Card.SuitType.Spade, rank = rank, rule = it) }
                }
            )
        }
    }

    fun onAction(navHostController: NavHostController, action: RulesAction) {
        when (action) {
            RulesAction.OnBackClicked -> {
                navHostController.navigateUp()
            }
        }
    }
}

data class RulesUiState(
    val cards: List<Card> = emptyList(),
    val closeScreen: Boolean = false,
)

sealed interface RulesAction {
    data object OnBackClicked : RulesAction
}