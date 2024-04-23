package com.organdonation.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.organdonation.ui.donateScreen.DonateScreen
import com.organdonation.ui.donorBodyForm.DonorBodyForm
import com.organdonation.ui.donorForm.DonorForm
import com.organdonation.ui.login.LoginScreen
import com.organdonation.ui.main.MainScreen
import com.organdonation.ui.register.RegisterScreen
import com.organdonation.ui.searchListing.SearchScreen
import com.organdonation.ui.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.SearchList.route) {
            SearchScreen(navController = navController)
        }
        composable(route = Screen.DonateScreen.route) {
            DonateScreen(navController = navController)
        }
        composable(route = Screen.DonorForm.route) {
            DonorForm(navController = navController)
        }
        composable(route = Screen.DonorBodyForm.route) {
            DonorBodyForm(navController = navController)
        }
    }

}