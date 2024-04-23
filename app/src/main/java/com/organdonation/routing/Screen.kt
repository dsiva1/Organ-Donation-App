package com.organdonation.routing

sealed class Screen(val route: String) {

    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object MainScreen: Screen("main_screen")
    object SearchList: Screen("search_list")
    object DonateScreen: Screen("donate_screen")
    object DonorForm: Screen("donor_form_screen")
    object DonorBodyForm: Screen("donate_body_screen")


}