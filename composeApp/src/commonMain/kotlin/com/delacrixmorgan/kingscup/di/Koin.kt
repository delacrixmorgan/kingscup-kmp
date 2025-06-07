package com.delacrixmorgan.kingscup.di

import com.delacrixmorgan.kingscup.core.local.LocalDataStore
import com.delacrixmorgan.kingscup.data.card.CardRepository
import com.delacrixmorgan.kingscup.data.preferences.PreferencesRepository
import com.delacrixmorgan.kingscup.platformModule
import com.delacrixmorgan.kingscup.ui.board.BoardViewModel
import com.delacrixmorgan.kingscup.ui.card.CardViewModel
import com.delacrixmorgan.kingscup.ui.loading.LoadingViewModel
import com.delacrixmorgan.kingscup.ui.rules.RulesViewModel
import com.delacrixmorgan.kingscup.ui.setup.SetupViewModel
import com.delacrixmorgan.kingscup.ui.start.StartViewModel
import com.delacrixmorgan.kingscup.ui.support.SupportViewModel
import com.delacrixmorgan.kingscup.usecase.BuildCardDeckUseCase
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            platformModule(),
            viewModelModule(),
            useCaseModule(),
            repositoryModule(),
        )
    }

fun viewModelModule() = module {
    viewModel { StartViewModel() }
    viewModel { SupportViewModel() }
    viewModel { SetupViewModel(get()) }
    viewModel { LoadingViewModel(get()) }
    viewModel { RulesViewModel() }
    viewModel { BoardViewModel(get()) }
    viewModel { CardViewModel(get()) }
}

fun useCaseModule() = module {
    single<BuildCardDeckUseCase> { BuildCardDeckUseCase(get(), get()) }
}

fun repositoryModule() = module {
    single<CardRepository> { CardRepository() }
    single<PreferencesRepository> { PreferencesRepository(get(qualifier = named(LocalDataStore.Preferences.name))) }
}
