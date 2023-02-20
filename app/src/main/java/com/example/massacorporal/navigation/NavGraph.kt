package com.example.massacorporal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.massacorporal.*


@Composable
fun SetupNavGraph(
    navController: NavHostController
){

    NavHost(
        navController = navController,
        startDestination = Screens.ScreenSplash.route)
    {

        composable(route = Screens.ScreenSplash.route){
            ScreenSplash(navController = navController)
        }

        composable(route = Screens.ScreenHome.route){
            ScreenHome(navController = navController)
        }

        composable(route = Screens.ScreenIAC.route){
            ScreenIAC(navController = navController)
        }

        composable(route = Screens.ScreenIMC.route){
            ScreenImc(navController = navController)
        }

        composable(route = Screens.ScreenINFO.route){
            ScreenINFO(navController = navController)
        }

        composable(route = Screens.ScreenConfig.route){
            ScreenConfig(navController = navController)
        }


    }
    
}