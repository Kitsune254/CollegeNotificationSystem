package com.kit.collegealertsystem.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kit.collegealertsystem.ui.theme.screens.academics.AcademicsScreen
import com.kit.collegealertsystem.ui.theme.screens.appdrawer.AppDrawer
import com.kit.collegealertsystem.ui.theme.screens.clubs.ClubScreen
import com.kit.collegealertsystem.ui.theme.screens.community.CommunityScreen
import com.kit.collegealertsystem.ui.theme.screens.home.HomeScreen
import com.kit.collegealertsystem.ui.theme.screens.login.LoginScreen
import com.kit.collegealertsystem.ui.theme.screens.notices.NoticesScreen
import com.kit.collegealertsystem.ui.theme.screens.registration.RegistrationScreen
import com.kit.collegealertsystem.ui.theme.screens.splash.SplashScreen
import com.kit.collegealertsystem.ui.theme.screens.sports.SportsScreen

@Composable
fun AppNavHost(modifier: Modifier= Modifier,
               navController: NavHostController = rememberNavController(),
               startDestination:String = ROUTE_SPLASH
) {
    NavHost(navController = navController, modifier=modifier, startDestination = startDestination){
        composable(ROUTE_REGISTER){
            RegistrationScreen(navController)
        }
        composable(ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(ROUTE_HOME){
            HomeScreen(navController)
        }
        composable(ROUTE_ACADEMICS){
            AcademicsScreen(navController)
        }
        composable(ROUTE_CLUBS){
            ClubScreen(navController)
        }
        composable(ROUTE_NOTICES){
            NoticesScreen(navController)
        }
        composable(ROUTE_COMMUNITY){
            CommunityScreen(navController)
        }
        composable(ROUTE_SPORT){
            SportsScreen(navController)
        }
        composable(ROUTE_SPLASH){
            SplashScreen(navController)
        }

    }
}