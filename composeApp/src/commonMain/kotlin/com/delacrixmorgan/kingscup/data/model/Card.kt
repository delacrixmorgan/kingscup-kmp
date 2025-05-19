package com.delacrixmorgan.kingscup.data.model

data class Card(
    val suit: SuitType,
    val rank: RankType,
    val rule: Rule
) {
    enum class SuitType {
        Spade,
        Heart,
        Club,
        Diamond,
        Joker;

        override fun toString(): String = when (this) {
            Spade -> "♠\uFE0F"
            Heart -> "♥\uFE0F"
            Club -> "♣\uFE0F"
            Diamond -> "♦\uFE0F"
            Joker -> "\uD83C\uDCCF"
        }
    }

    enum class RankType {
        King,
        Queen,
        Jack,
        Ten,
        Nine,
        Eight,
        Seven,
        Six,
        Five,
        Four,
        Three,
        Two,
        Ace,
        Joker;

        override fun toString(): String = when (this) {
            King -> "K"
            Queen -> "Q"
            Jack -> "J"
            Ten -> "10"
            Nine -> "9"
            Eight -> "8"
            Seven -> "7"
            Six -> "6"
            Five -> "5"
            Four -> "4"
            Three -> "3"
            Two -> "2"
            Ace -> "A"
            Joker -> "Joker"
        }
    }
}