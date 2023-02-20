package com.example.massacorporal.navigation

sealed class Screens(val route: String){

    object ScreenSplash: Screens("splash_screen")
    object ScreenHome: Screens("home_screen")
    object ScreenIMC: Screens("imc_screen")
    object ScreenIAC: Screens("iac_screen")
    object ScreenINFO: Screens("info_screen")
    object ScreenConfig: Screens("config_screen")

}
