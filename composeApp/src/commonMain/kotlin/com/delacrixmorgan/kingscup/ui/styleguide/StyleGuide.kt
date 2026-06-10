package com.delacrixmorgan.kingscup.ui.styleguide

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.delacrixmorgan.kingscup.ui.component.AppBar
import com.delacrixmorgan.kingscup.ui.component.NavigationBackIcon
import com.delacrixmorgan.kingscup.ui.styleguide.color.ColorScreen
import com.delacrixmorgan.kingscup.ui.styleguide.font.FontScreen
import com.delacrixmorgan.kingscup.ui.styleguide.theme.ThemeScreen
import com.delacrixmorgan.kingscup.ui.styleguide.theme.ThemeViewModel
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.ic_contrast
import kingscup.composeapp.generated.resources.ic_palette
import kingscup.composeapp.generated.resources.ic_text_fields
import kingscup.composeapp.generated.resources.styleGuide_title
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyleGuideRoot(themeViewModel: ThemeViewModel, navHostController: NavHostController) {
    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val styleNavController = rememberNavController()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppBar(
                title = stringResource(Res.string.styleGuide_title),
                navigationIcon = { NavigationBackIcon { navHostController.navigateUp() } },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = { BottomNavigationBar(styleNavController) }
    ) { innerPadding ->
        NavHost(
            navController = styleNavController,
            startDestination = DashboardBottomNavItem.Color.route
        ) {
            composable<StyleGuideRoutes.Color> { ColorScreen(Modifier.padding(innerPadding)) }
            composable<StyleGuideRoutes.Font> { FontScreen(Modifier.padding(innerPadding)) }
            composable<StyleGuideRoutes.Theme> {
                ThemeScreen(
                    modifier = Modifier.padding(innerPadding),
                    state = themeViewModel.state.collectAsStateWithLifecycle().value,
                    onAction = { themeViewModel.onAction(it) }
                )
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(
    navHostController: NavHostController,
    haptic: HapticFeedback = LocalHapticFeedback.current,
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        DashboardBottomNavItem.entries.forEach { navItem ->
            val selected by remember(currentRoute) {
                derivedStateOf { currentRoute == navItem.route::class.qualifiedName }
            }
            NavigationBarItem(
                icon = { Icon(painter = painterResource(navItem.icon), contentDescription = navItem.title) },
                selected = selected,
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
                    if (selected) return@NavigationBarItem
                    navHostController.navigate(navItem.route) {
                        popUpTo(navHostController.graph.findStartDestination().id)
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

enum class DashboardBottomNavItem(
    val title: String,
    val route: StyleGuideRoutes,
    val icon: DrawableResource,
) {
    Color(
        "Color",
        StyleGuideRoutes.Color,
        Res.drawable.ic_palette
    ),
    Font(
        "Font",
        StyleGuideRoutes.Font,
        Res.drawable.ic_text_fields
    ),
    Theme(
        "Theme",
        StyleGuideRoutes.Theme,
        Res.drawable.ic_contrast
    ),
}

sealed class StyleGuideRoutes {
    @Serializable
    data object Color : StyleGuideRoutes()

    @Serializable
    data object Font : StyleGuideRoutes()

    @Serializable
    data object Theme : StyleGuideRoutes()
}