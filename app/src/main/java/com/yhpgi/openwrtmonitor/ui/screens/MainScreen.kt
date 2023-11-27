package com.yhpgi.openwrtmonitor.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yhpgi.openwrtmonitor.R
import com.yhpgi.openwrtmonitor.ui.activity.MainActivity
import com.yhpgi.openwrtmonitor.ui.navigation.BottomBarItem
import com.yhpgi.openwrtmonitor.ui.navigation.Screens
import com.yhpgi.openwrtmonitor.ui.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    mainActivity: MainActivity
) {
    val navController = rememberNavController()
    val listOfBottomBarItem = listOf(
        BottomBarItem(
            label = stringResource(id = R.string.navbar_home),
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
            route = Screens.MainScreen.name
        ),
        BottomBarItem(
            label = stringResource(id = R.string.navbar_luci),
            unselectedIcon = Icons.Outlined.List,
            selectedIcon = Icons.Filled.List,
            route = Screens.LuciScreen.name
        ),
        BottomBarItem(
            label = stringResource(id = R.string.navbar_clash),
            unselectedIcon = Icons.Outlined.ShoppingCart,
            selectedIcon = Icons.Filled.ShoppingCart,
            route = Screens.OpenClashScreen.name
        ),
        BottomBarItem(
            label = stringResource(id = R.string.navbar_settings),
            unselectedIcon = Icons.Outlined.Settings,
            selectedIcon = Icons.Filled.Settings,
            route = Screens.SettingsScreen.name
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                listOfBottomBarItem.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                        onClick = {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (currentDestination?.hierarchy?.any { it.route == navItem.route } == true) navItem.selectedIcon else navItem.unselectedIcon,
                                contentDescription = navItem.label)
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        },
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Screens.MainScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(
                route = Screens.MainScreen.name,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() }
            ) {
                HomeScreen()
            }
            composable(
                route = Screens.LuciScreen.name,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() }
            ) {
                LuciScreen(mainViewModel, mainActivity)
            }
            composable(
                route = Screens.OpenClashScreen.name,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() }
            ) {
                OpenClashScreen(mainViewModel, mainActivity)
            }
            composable(
                route = Screens.SettingsScreen.name,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() }
            ) {
                SettingsScreen(mainViewModel, mainActivity)

            }
        }
    }
}
