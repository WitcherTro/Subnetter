package com.example.subnetter.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.subnetter.ui.screen.CalculatorScreen
import com.example.subnetter.ui.screen.Screens
import com.example.subnetter.ui.screen.TableScreen
import com.example.subnetter.ui.screen.TrainingScreen

/**
 * Composable function that displays a bottom navigation bar.
 *
 * The navigation bar includes items for the Calculator, Training, and Table screens.
 * When an item is selected, the corresponding screen is displayed.
 */
@Composable
fun BottomNavigationBar() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                bottomNavigationItems().forEachIndexed { _, navigationItem ->
                    NavigationBarItem(
                        selected = navigationItem.route == currentDestination?.route,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                painter = navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Calculator.route,
            modifier = Modifier.padding(paddingValues = paddingValues)) {
            composable(Screens.Calculator.route) {
                CalculatorScreen()
            }
            composable(Screens.Training.route) {
                TrainingScreen()
            }
            composable(Screens.Table.route) {
                TableScreen()
            }
        }
    }
}