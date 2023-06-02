package com.example.massacorporal.screens.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            imagem = R.drawable.fitimc,
            textoBaixo = "Imc",
            rota = Screens.ScreenIMC.route
        )
        BotaoPadrao(
            nav = navController,
            descricao = "botão iac",
            imagem = R.drawable.fitiac,
            textoBaixo = "Iac",
            rota = Screens.ScreenIAC.route
        )
        /*BotaoPadrao(
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
        )*/
        BotaoPadrao(
            nav = navController,
            descricao = "botão dados",
            imagem = R.drawable.dataset,
            textoBaixo = "Dados imc",
            rota = Screens.ScreenRegistrosIMC.route)

        BotaoPadrao(
            nav = navController,
            descricao = "botão dados",
            imagem = R.drawable.dataset,
            textoBaixo = "Dados iac",
            rota = Screens.ScreenRegistrosIMC.route)
    }
}


@Composable
fun BotaoPadrao(
    nav: NavHostController,
    descricao: String,
    imagem: Int,
    textoBaixo: String,
    rota: String){


    Column(
        horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape),
            shape = CircleShape,
            shadowElevation = 10.dp
        ) {
            IconButton(
                onClick = {
                    nav.navigate(route = rota)
                },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(5.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(50.dp),
                    painter = painterResource(id = imagem),
                    contentDescription = descricao,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Text(modifier = Modifier.padding(5.dp),
            text = textoBaixo, style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
    }


}