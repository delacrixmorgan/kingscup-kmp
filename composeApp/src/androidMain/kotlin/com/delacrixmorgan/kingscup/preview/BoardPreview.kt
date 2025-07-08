package com.delacrixmorgan.kingscup.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.delacrixmorgan.kingscup.data.card.model.Card
import com.delacrixmorgan.kingscup.data.card.model.Normal
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.board.BoardScreen
import com.delacrixmorgan.kingscup.ui.board.BoardUiState
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
private fun BoardPreview() {
    AppTheme {
        val cards = Card.SuitType.entries
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

        BoardScreen(
            state = BoardUiState(
                cards = cards,
                gameInSession = true,
                kingCounter = 2
            ),
            onAction = {}
        )
    }
}