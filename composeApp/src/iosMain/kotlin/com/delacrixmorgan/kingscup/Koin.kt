package com.delacrixmorgan.kingscup

import com.delacrixmorgan.kingscup.di.repositoryModule
import com.delacrixmorgan.kingscup.di.useCaseModule
import com.delacrixmorgan.kingscup.di.viewModelModule
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            platformModule(),
            viewModelModule(),
            useCaseModule(),
            repositoryModule(),
        )
    }
}

object KoinHelper : KoinComponent