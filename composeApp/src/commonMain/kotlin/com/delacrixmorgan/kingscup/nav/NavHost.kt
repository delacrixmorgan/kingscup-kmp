package com.delacrixmorgan.kingscup.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.delacrixmorgan.kingscup.ui.board.BoardRoot
import com.delacrixmorgan.kingscup.ui.board.BoardViewModel
import com.delacrixmorgan.kingscup.ui.card.CardRoot
import com.delacrixmorgan.kingscup.ui.card.CardViewModel
import com.delacrixmorgan.kingscup.ui.loading.LoadingRoot
import com.delacrixmorgan.kingscup.ui.loading.LoadingViewModel
import com.delacrixmorgan.kingscup.ui.rules.RulesRoot
import com.delacrixmorgan.kingscup.ui.rules.RulesViewModel
import com.delacrixmorgan.kingscup.ui.setup.SetupRoot
import com.delacrixmorgan.kingscup.ui.setup.SetupViewModel
import com.delacrixmorgan.kingscup.ui.start.StartRoot
import com.delacrixmorgan.kingscup.ui.start.StartViewModel
import com.delacrixmorgan.kingscup.ui.style.StyleRoot
import com.delacrixmorgan.kingscup.ui.support.SupportRoot
import com.delacrixmorgan.kingscup.ui.support.SupportViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavHost(navHostController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.Style
//        startDestination = Routes.Start
    ) {
        formGraph(navHostController)
    }
}

fun NavGraphBuilder.formGraph(navHostController: NavHostController) {
    composable<Routes.Start> { StartRoot(viewModel = koinViewModel<StartViewModel>(), navHostController) }
    composable<Routes.Support> { SupportRoot(viewModel = koinViewModel<SupportViewModel>(), navHostController) }
    composable<Routes.Setup> { SetupRoot(viewModel = koinViewModel<SetupViewModel>(), navHostController) }
    composable<Routes.Rules> { RulesRoot(viewModel = koinViewModel<RulesViewModel>(), navHostController) }
    composable<Routes.Loading> { LoadingRoot(viewModel = koinViewModel<LoadingViewModel>(), navHostController) }
    composable<Routes.Board> { BoardRoot(viewModel = koinViewModel<BoardViewModel>(), navHostController) }
    composable<Routes.Card> { CardRoot(viewModel = koinViewModel<CardViewModel>(), navHostController) }
    composable<Routes.Style> { StyleRoot() }
}