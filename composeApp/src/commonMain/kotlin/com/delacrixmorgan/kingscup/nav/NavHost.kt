package com.delacrixmorgan.kingscup.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.delacrixmorgan.kingscup.ui.appinfo.AppInfoRoot
import com.delacrixmorgan.kingscup.ui.board.BoardRoot
import com.delacrixmorgan.kingscup.ui.card.CardRoot
import com.delacrixmorgan.kingscup.ui.loading.LoadingRoot
import com.delacrixmorgan.kingscup.ui.rules.RulesRoot
import com.delacrixmorgan.kingscup.ui.setup.SetupRoot
import com.delacrixmorgan.kingscup.ui.start.StartRoot
import com.delacrixmorgan.kingscup.ui.styleguide.StyleGuideRoot
import com.delacrixmorgan.kingscup.ui.support.SupportRoot
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavHost(navHostController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.Support,
//        startDestination = Routes.Start,
    ) {
        formGraph(navHostController)
    }
}

fun NavGraphBuilder.formGraph(navHostController: NavHostController) {
    composable<Routes.Start> { StartRoot(viewModel = koinViewModel(), navHostController) }
    composable<Routes.Support> { SupportRoot(viewModel = koinViewModel(), navHostController) }
    composable<Routes.AppInfo> { AppInfoRoot(viewModel = koinViewModel(), navHostController) }
    composable<Routes.StyleGuide> { StyleGuideRoot(themeViewModel = koinViewModel(), navHostController) }

    composable<Routes.Setup> { SetupRoot(viewModel = koinViewModel(), navHostController) }
    composable<Routes.Rules> { RulesRoot(viewModel = koinViewModel(), navHostController) }
    composable<Routes.Loading> { LoadingRoot(viewModel = koinViewModel(), navHostController) }
    composable<Routes.Board> { BoardRoot(viewModel = koinViewModel(), navHostController) }
    composable<Routes.Card> { CardRoot(viewModel = koinViewModel(), navHostController) }
}