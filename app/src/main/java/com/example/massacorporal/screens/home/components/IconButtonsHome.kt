package com.example.massacorporal.screens.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.massacorporal.R
import com.example.massacorporal.navigation.Screens


@Composable
fun IconsButtonsHome(navController: NavHostController){


    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        BotaoPadrao(
            nav = navController,
            descricao = "botão imc",
            imagem = R.drawable.calculate_24,
            textoBaixo = "Imc",
            rota = Screens.ScreenIMC.route
        )
        BotaoPadrao(
            nav = navController,
            descricao = "botão iac",
            imagem = R.drawable.calculate_24,
            textoBaixo = "Iac",
            rota = Screens.ScreenIAC.route
        )
        BotaoPadrao(
            nav = navController,
            descricao = "botão info",
            imagem = R.drawable.info_24,
            textoBaixo = "Info",
            rota = Screens.ScreenINFO.route
        )
        BotaoPadrao(
            nav = navController,
            descricao = "botão config",
            imagem = R.drawable.baseline_settings_24,
            textoBaixo = "Config",
            rota = Screens.ScreenConfig.route
        )
    }
}


@Composable
fun BotaoPadrao(
    nav: NavHostController,
    descricao: String,
    imagem: Int,
    textoBaixo: String,
    rota: String){


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = {
                nav.navigate(route = rota)
            },
            modifier = Modifier.border(width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp))
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = imagem),
                contentDescription = descricao,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Text(modifier = Modifier.padding(10.dp),
            text = textoBaixo, style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
    }


}