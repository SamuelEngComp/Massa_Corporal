package com.example.massacorporal.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.massacorporal.components.Datas
import com.example.massacorporal.components.Estados
import com.example.massacorporal.navigation.Screens

@Composable
fun ContentHomeIAC(
    navController: NavHostController,
    textoIAC: String,
    indiceIac: String){


    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(route = Screens.ScreenIAC.route)
                },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(10.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    ContentHomeTextoIAC(textoIAC = textoIAC)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    ContentHomeValoresIAC(indiceIac = indiceIac)
                }
            }
        }
    }


}

@Composable
fun ContentHomeValoresIAC(indiceIac: String){
    if (Estados.controleEstadoIAC)
        Text(
            text = " ${Estados.estadoIac}",
            style = MaterialTheme.typography.titleSmall
        )

    Text(
        text = indiceIac,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold
    )
    Text(text = " ${Datas.dataIAC} ",
        style = MaterialTheme.typography.titleSmall)
}


@Composable
fun ContentHomeTextoIAC(textoIAC: String){
    Text(
        text = textoIAC,
        style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = CircleShape
            ).padding(10.dp)
    )
}