package com.delacrixmorgan.kingscup.data.repository

import com.delacrixmorgan.kingscup.data.model.Card
import com.delacrixmorgan.kingscup.data.model.Jokers
import com.delacrixmorgan.kingscup.data.model.Normal
import com.delacrixmorgan.kingscup.data.model.Rule

class DealerRepository {
    private val previousCard: Card? = null
    val deck: MutableList<Card> = mutableListOf()
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
                    rule?.let { Card(suit = suitType, rank = rank, rule = it) }
                }
            }
        val shuffledDeck = if (isJokersEnabled) {
            val jokerDeck: List<Card> = jokerRules.shuffled().take(4).map { rule ->
                Card(suit = Card.SuitType.Joker, rank = Card.RankType.Joker, rule = rule)
            }
            normalDeck + jokerDeck
        } else {
            normalDeck
        }.shuffled()
        deck.addAll(shuffledDeck)
    }
}