package com.delacrixmorgan.kingscup.di

import com.delacrixmorgan.kingscup.data.card.CardRepository
import com.delacrixmorgan.kingscup.platformModule
import com.delacrixmorgan.kingscup.ui.board.BoardViewModel
import com.delacrixmorgan.kingscup.ui.card.CardViewModel
import com.delacrixmorgan.kingscup.ui.loading.LoadingViewModel
import com.delacrixmorgan.kingscup.ui.rules.RulesViewModel
import com.delacrixmorgan.kingscup.ui.setup.SetupViewModel
import com.delacrixmorgan.kingscup.ui.start.StartViewModel
import com.delacrixmorgan.kingscup.ui.support.SupportViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            platformModule(),
            viewModelModule(),
            serviceModule(),
            repositoryModule(),
        )
    }

fun viewModelModule() = module {
    viewModel { StartViewModel() }
    viewModel { SupportViewModel() }
    viewModel {
        SetupViewModel(

        )
    }
    viewModel { LoadingViewModel() }
    viewModel { RulesViewModel() }
    viewModel { BoardViewModel(get()) }
    viewModel { CardViewModel(get()) }
}

fun serviceModule() = module {
}

fun repositoryModule() = module {
    single<CardRepository> { CardRepository() }
}
