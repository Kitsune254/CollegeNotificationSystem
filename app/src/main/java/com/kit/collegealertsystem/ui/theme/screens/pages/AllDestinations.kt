package com.kit.collegealertsystem.ui.theme.screens.pages

import androidx.navigation.NavHostController
import com.kit.collegealertsystem.navigation.ROUTE_ACADEMICS
import com.kit.collegealertsystem.navigation.ROUTE_HOME
import com.kit.collegealertsystem.ui.theme.screens.pages.AllDestinations.HOME

object AllDestinations {
    const val HOME = "Home"
    const val ACADEMICS = "Settings"
}

class AppNavigationActions(private val navController: NavHostController) {

    fun navigateToHome() {
        navController.navigate(ROUTE_HOME) {
            popUpTo(HOME)
        }
    }

    fun navigateToSettings() {
        navController.navigate(ROUTE_ACADEMICS) {
//            launchSingleTop = true
//            restoreState = true
            popUpTo(ROUTE_ACADEMICS)
        }
    }
}