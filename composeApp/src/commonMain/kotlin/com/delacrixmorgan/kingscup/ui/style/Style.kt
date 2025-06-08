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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.delacrixmorgan.kingscup.ui.style.color.ColorScreen
import kotlinx.serialization.Serializable

@Composable
fun StyleRoot() {
    val styleNavController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(styleNavController) }
    ) { innerPadding ->
        NavHost(
            navController = styleNavController,
            startDestination = DashboardBottomNavItem.Color.route
        ) {
            composable<StyleRoutes.Color> {
                ColorScreen(Modifier.padding(innerPadding))
            }
            composable<StyleRoutes.Font> {
                ColorScreen(Modifier.padding(innerPadding))
            }
            composable<StyleRoutes.Theme> {
                ColorScreen(Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(navHostController: NavHostController) {
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