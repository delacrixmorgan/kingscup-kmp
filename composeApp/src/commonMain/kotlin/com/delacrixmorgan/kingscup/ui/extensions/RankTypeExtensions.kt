package com.delacrixmorgan.kingscup.ui.extensions

import com.delacrixmorgan.kingscup.data.card.model.Card
import com.delacrixmorgan.kingscup.data.card.model.Normal
import com.delacrixmorgan.kingscup.data.card.model.Rule

fun Card.RankType.defaultRule(): Rule? = when (this) {
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