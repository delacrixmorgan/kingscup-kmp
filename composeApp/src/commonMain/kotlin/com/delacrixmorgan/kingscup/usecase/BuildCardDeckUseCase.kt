package com.delacrixmorgan.kingscup.usecase

import com.delacrixmorgan.kingscup.data.card.CardRepository
import com.delacrixmorgan.kingscup.data.preferences.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.first

class BuildCardDeckUseCase(
    private val preferencesRepository: PreferencesRepository,
    private val cardRepository: CardRepository,
) : Flow<Unit> {
    override suspend fun collect(collector: FlowCollector<Unit>) {
        val jokerEnabled = preferencesRepository.jokerEnabledFlow.first()
        cardRepository.buildDeck(jokerEnabled)
        collector.emit(Unit)
    }
}