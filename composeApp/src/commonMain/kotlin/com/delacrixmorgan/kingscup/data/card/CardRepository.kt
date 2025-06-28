package com.delacrixmorgan.kingscup.data.card

import com.delacrixmorgan.kingscup.data.card.model.Card
import com.delacrixmorgan.kingscup.data.card.model.Normal
import com.delacrixmorgan.kingscup.ui.extensions.defaultRule
import com.delacrixmorgan.kingscup.ui.extensions.jokerRules
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
    var activeJoker: Card? = null
        private set

    private var activeIndex: Int? = null

    private val _gameInSession = MutableStateFlow(false)
    val gameInSession: StateFlow<Boolean> = _gameInSession.asStateFlow()

    private val _kingCounter = MutableStateFlow(0)
    val kingCounter: StateFlow<Int> = _kingCounter.asStateFlow()

    private val _jokers = MutableStateFlow(emptyList<Card>())
    val jokers: StateFlow<List<Card>> = _jokers.asStateFlow()

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
        }.shuffledFirst(
            kingsFirst = true,
            jokersFirst = true
        )
        _cards.value = shuffledDeck
        _kingCounter.value = 0
        _jokers.value = emptyList()
        _gameInSession.value = false

        activeCard = null
        activeJoker = null
    }

    private fun List<Card>.shuffledFirst(
        kingsFirst: Boolean = false,
        jokersFirst: Boolean = false,
    ): List<Card> {
        return this.shuffled()
            .sortedWith(compareBy {
                when {
                    kingsFirst && it.rank == Card.RankType.King -> 0
                    jokersFirst && it.rank == Card.RankType.Joker -> 1
                    else -> 2
                }
            })
    }

    fun drawCard(index: Int) {
        activeCard = cards.value.getOrNull(index)
        activeIndex = index
        _gameInSession.value = true
    }

    fun discardCard() {
        activeCard?.let {
            if (it.rule == Normal.King) _kingCounter.value += 1
            if (it.suit == Card.SuitType.Joker) {
                val currentList = _jokers.value.toMutableList()
                currentList.add(it)
                _jokers.value = currentList
            }
            _cards.value -= it
        }

        activeCard = null
        activeIndex = null
    }

    fun showJoker(card: Card) {
        activeJoker = card
    }

    fun dismissJoker() {
        activeJoker = null
    }
}