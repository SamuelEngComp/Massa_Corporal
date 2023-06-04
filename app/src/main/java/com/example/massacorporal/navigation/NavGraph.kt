package com.example.massacorporal.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.massacorporal.*
import com.example.massacorporal.screens.ScreenConfig
import com.example.massacorporal.screens.home.ScreenHome
import com.example.massacorporal.screens.ScreenIAC
import com.example.massacorporal.screens.info.ScreenINFO
import com.example.massacorporal.screens.imc.ScreenImc
import com.example.massacorporal.screens.ScreenSplash

import com.example.massacorporal.screens.registros.ScreenRegistrosIMC
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
            ScreenHome(navController = navController, model = themeViewModel)
        }

        composable(route = Screens.ScreenIAC.route){
            ScreenIAC(navController = navController)
        }

        composable(route = Screens.ScreenIMC.route){
            ScreenImc(
                navController = navController,
                model = themeViewModel
            )
        }

        composable(route = Screens.ScreenINFO.route){
            ScreenINFO(
                navController = navController,
                model = themeViewModel
            )
        }

        composable(route = Screens.ScreenConfig.route){
            ScreenConfig(navController = navController,
                dataStore = dataStoreUtil,
                themeViewModel = themeViewModel
            )
        }

        composable(route = Screens.ScreenRegistrosIMC.route){
            ScreenRegistrosIMC(navController = navController,
                dataStore = dataStoreUtil,
                themeViewModel = themeViewModel
            )
        }


    }
    
}