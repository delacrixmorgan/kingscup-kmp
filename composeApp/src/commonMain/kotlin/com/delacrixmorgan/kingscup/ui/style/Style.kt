package com.delacrixmorgan.kingscup.ui.style

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Contrast
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.TextFields
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.delacrixmorgan.kingscup.ui.style.color.ColorScreen
import com.delacrixmorgan.kingscup.ui.style.font.FontScreen
import com.delacrixmorgan.kingscup.ui.style.theme.ThemeScreen
import com.delacrixmorgan.kingscup.ui.style.theme.ThemeViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StyleRoot(themeViewModel: ThemeViewModel) {
    val styleNavController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(styleNavController) }
    ) { innerPadding ->
        NavHost(
            navController = styleNavController,
            startDestination = DashboardBottomNavItem.Color.route
        ) {
            composable<StyleRoutes.Color> { ColorScreen(Modifier.padding(innerPadding)) }
            composable<StyleRoutes.Font> { FontScreen(Modifier.padding(innerPadding)) }
            composable<StyleRoutes.Theme> {
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
    val route: StyleRoutes,
    val icon: ImageVector,
) {
    Color(
        "Color",
        StyleRoutes.Color,
        Icons.Rounded.Palette
    ),
    Font(
        "Font",
        StyleRoutes.Font,
        Icons.Rounded.TextFields
    ),
    Theme(
        "Theme",
        StyleRoutes.Theme,
        Icons.Rounded.Contrast
    ),
}

sealed class StyleRoutes {
    @Serializable
    data object Color : StyleRoutes()

    @Serializable
    data object Font : StyleRoutes()

    @Serializable
    data object Theme : StyleRoutes()
}