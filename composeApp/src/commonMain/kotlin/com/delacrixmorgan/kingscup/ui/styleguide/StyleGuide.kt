package com.delacrixmorgan.kingscup.ui.styleguide

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Contrast
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.TextFields
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
import androidx.compose.ui.graphics.vector.ImageVector
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
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyleGuideRoot(themeViewModel: ThemeViewModel, navHostController: NavHostController) {
    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val styleNavController = rememberNavController()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppBar(
                title = "Style Guide",
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
                icon = { Icon(navItem.icon, contentDescription = navItem.title) },
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
    val icon: ImageVector,
) {
    Color(
        "Color",
        StyleGuideRoutes.Color,
        Icons.Rounded.Palette
    ),
    Font(
        "Font",
        StyleGuideRoutes.Font,
        Icons.Rounded.TextFields
    ),
    Theme(
        "Theme",
        StyleGuideRoutes.Theme,
        Icons.Rounded.Contrast
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