package com.example.massacorporal.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import com.example.massacorporal.components.Indices

import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.screens.components.Anuncio
import com.example.massacorporal.screens.home.components.ContentHomeIAC
import com.example.massacorporal.screens.home.components.ContentHomeIMC


import com.example.massacorporal.screens.home.components.HearderHome
import com.example.massacorporal.screens.home.components.IconsButtonsHome


@Composable
fun ScreenHome(navController: NavHostController) {

    val bemVindo = "Bem-Vindo"
    val cuidarSaude = "Cuide da Saúde"
    val monitorarIndices = "Monitore os Índices"

    var indiceIMC = Indices.imc
    var indiceIAC = Indices.iac


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            HearderHome(
                textoBemVindo = bemVindo,
                textoCuidarSaude = cuidarSaude,
                textoMonitarIndices = monitorarIndices
            )
            ContentHomeIMC(
                navController = navController,
                textoIMC = " IMC ",
                indiceImc = indiceIMC
            )

            ContentHomeIAC(
                navController = navController,
                textoIAC = " IAC ",
                indiceIac = indiceIAC
            )

            Spacer(modifier = Modifier.height(25.dp))

            //botoes
            IconsButtonsHome(navController = navController)
        }
        // linha do anuncio
        Anuncio()
    }
}



