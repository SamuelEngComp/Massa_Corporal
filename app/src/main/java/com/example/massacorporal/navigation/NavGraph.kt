package com.example.massacorporal.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.massacorporal.*
import com.example.massacorporal.util.DataStoreUtil
import com.example.massacorporal.viewmodel.ThemeViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    dataStoreUtil: DataStoreUtil,
    themeViewModel: ThemeViewModel
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
            ScreenConfig(navController = navController, dataStore = dataStoreUtil ,themeViewModel = themeViewModel)
        }


    }
    
}