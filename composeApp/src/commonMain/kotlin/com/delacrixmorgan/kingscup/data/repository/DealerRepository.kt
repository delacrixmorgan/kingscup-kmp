package com.delacrixmorgan.kingscup.data.repository

import com.delacrixmorgan.kingscup.data.model.Card
import com.delacrixmorgan.kingscup.data.model.Jokers
import com.delacrixmorgan.kingscup.data.model.Normal
import com.delacrixmorgan.kingscup.data.model.Rule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class DealerRepository {
    private val _cards = MutableStateFlow(emptyList<Card>())
    val cards: StateFlow<List<Card>> = _cards.asStateFlow()
    var activeCard: Card? = null
        private set
    private var activeIndex: Int? = null

    private val isJokersEnabled: Boolean = false
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

    init {
        buildDeck()
    }

    @OptIn(ExperimentalUuidApi::class)
    fun buildDeck() {
        val normalDeck: List<Card> = Card.SuitType.entries
            .filterNot { it == Card.SuitType.Joker }
            .flatMap { suitType ->
                Card.RankType.entries.mapNotNull { rank ->
                    val rule = when (rank) {
                        Card.RankType.King -> Normal.King
                        Card.RankType.Queen -> Normal.QuestionMaster
                        Card.RankType.Jack -> Normal.NeverHaveIEver
                        Card.RankType.Ten -> Normal.Categories
                        Card.RankType.Nine -> Normal.SnakeEyes
                        Card.RankType.Eight -> Normal.Mate
                        Card.RankType.Seven -> Normal.Heaven
                        Card.RankType.Six -> Normal.Chicks
                        Card.RankType.Five -> Normal.Dudes
                        Card.RankType.Four -> Normal.ThumbMaster
                        Card.RankType.Three -> Normal.Me
                        Card.RankType.Two -> Normal.You
                        Card.RankType.Ace -> Normal.Waterfall
                        else -> null
                    }
                    rule?.let { Card(uuid = Uuid.random().toString(), suit = suitType, rank = rank, rule = it) }
                }
            }
        val shuffledDeck = if (isJokersEnabled) {
            val jokerDeck: List<Card> = jokerRules.shuffled().take(4).map { rule ->
                Card(uuid = Uuid.random().toString(), suit = Card.SuitType.Joker, rank = Card.RankType.Joker, rule = rule)
            }
            normalDeck + jokerDeck
        } else {
            normalDeck
        }.shuffled()
        _cards.value = shuffledDeck
    }

    fun drawCard(index: Int) {
        activeCard = cards.value.getOrNull(index)
        activeIndex = index
    }

    fun discardCard() {
        if (activeCard == null || activeIndex == null) return
        activeIndex?.let { _cards.value -= cards.value[it] }
        activeCard = null
        activeIndex = null
    }
}