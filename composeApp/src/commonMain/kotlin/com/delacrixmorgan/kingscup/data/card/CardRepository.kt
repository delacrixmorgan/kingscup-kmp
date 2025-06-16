package com.delacrixmorgan.kingscup.data.card

import com.delacrixmorgan.kingscup.data.card.model.Card
import com.delacrixmorgan.kingscup.data.card.model.Jokers
import com.delacrixmorgan.kingscup.data.card.model.Normal
import com.delacrixmorgan.kingscup.data.card.model.Rule
import com.delacrixmorgan.kingscup.ui.extensions.defaultRule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class CardRepository {
    private val _cards = MutableStateFlow(emptyList<Card>())
    val cards: StateFlow<List<Card>> = _cards.asStateFlow()
    var activeCard: Card? = null
        private set
    private var activeIndex: Int? = null

    private val _gameInSession = MutableStateFlow(false)
    val gameInSession: StateFlow<Boolean> = _gameInSession.asStateFlow()

    private val _kingCounter = MutableStateFlow(0)
    val kingCounter: StateFlow<Int> = _kingCounter.asStateFlow()

    private val jokerRules: List<Rule>
        get() = listOf(
            Jokers.Viking,
            Jokers.NoFirstNames,
            Jokers.NoPointing,
            Jokers.TRexArms,
            Jokers.BeLikeBilly,
            Jokers.IWillTellYouWhat,
            Jokers.NoPhones,
            Jokers.NoSwearing,
            Jokers.Photographer,
            Jokers.LastOneLaughing,
            Jokers.LeftHand,
            Jokers.Toilet
        )

    @OptIn(ExperimentalUuidApi::class)
    fun buildDeck(jokerEnabled: Boolean) {
        val normalDeck: List<Card> = Card.SuitType.entries
            .filterNot { it == Card.SuitType.Joker }
            .flatMap { suitType ->
                Card.RankType.entries.mapNotNull { rank ->
                    rank.defaultRule()?.let { Card(uuid = Uuid.random().toString(), suit = suitType, rank = rank, rule = it) }
                }
            }
        val shuffledDeck = if (jokerEnabled) {
            val jokerDeck: List<Card> = jokerRules.shuffled().take(4).map { rule ->
                Card(uuid = Uuid.random().toString(), suit = Card.SuitType.Joker, rank = Card.RankType.Joker, rule = rule)
            }
            normalDeck + jokerDeck
        } else {
            normalDeck
        }.shuffled()
        _cards.value = shuffledDeck
        _kingCounter.value = 0
        _gameInSession.value = false
    }

    fun drawCard(index: Int) {
        activeCard = cards.value.getOrNull(index)
        activeIndex = index
        _gameInSession.value = true
    }

    fun discardCard() {
        if (activeCard == null || activeIndex == null) return
        activeIndex?.let {
            if (cards.value[it].rule == Normal.King) _kingCounter.value += 1
            _cards.value -= cards.value[it]
        }
        activeCard = null
        activeIndex = null
    }
}