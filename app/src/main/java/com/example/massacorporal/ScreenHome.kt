package com.example.massacorporal

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.massacorporal.components.Datas
import com.example.massacorporal.components.Estados
import com.example.massacorporal.components.Indices

import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.ui.theme.AzulNeve


@Composable
fun ScreenHome(navController: NavHostController){

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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = bemVindo,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.subtitle2)

                        Text(
                            text = cuidarSaude,
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.primary
                        )

                        Text(
                            text = monitorarIndices,
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.primary
                        )

                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                if (Estados.controleEstadoIMC)
                                    Text(
                                        text = " ${Estados.estadoImc}",
                                        style = MaterialTheme.typography.caption
                                    )

                                Text(
                                    text = " $indiceIMC ",
                                    style = MaterialTheme.typography.h2,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = " ${Datas.dataIMC} ",
                                    style = MaterialTheme.typography.caption
                                )
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally){
                                Text(
                                    text = " IMC ",
                                    style = MaterialTheme.typography.h2,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .background(
                                            color = MaterialTheme.colors.background,
                                            shape = CircleShape
                                        )
                                        .padding(10.dp)
                                )
                            }
                        }
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally){
                                Text(
                                    text = " IAC ",
                                    style = MaterialTheme.typography.h2,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .background(
                                            color = MaterialTheme.colors.background,
                                            shape = CircleShape
                                        )
                                        .padding(10.dp)
                                )
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally){

                                if (Estados.controleEstadoIAC)
                                    Text(
                                        text = " ${Estados.estadoIac}",
                                        style = MaterialTheme.typography.caption
                                    )

                                Text(
                                    text = " $indiceIAC ",
                                    style = MaterialTheme.typography.h2,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = " ${Datas.dataIAC} ",
                                    style = MaterialTheme.typography.caption)
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(25.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        OutlinedButton(
                            onClick = {
                                navController.navigate(route = Screens.ScreenIMC.route)
                            },
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 10.dp,
                                pressedElevation = 0.dp,
                                focusedElevation = 15.dp,
                                hoveredElevation = 10.dp
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(40.dp),
                                painter = painterResource(id = R.drawable.calculate_24),
                                contentDescription = "botao imc",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "Imc", style = MaterialTheme.typography.caption,
                            fontWeight = FontWeight.Bold
                        )

                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedButton(
                            onClick = {
                                navController.navigate(route = Screens.ScreenIAC.route)
                            },
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 10.dp,
                                pressedElevation = 0.dp,
                                focusedElevation = 15.dp,
                                hoveredElevation = 10.dp
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(40.dp),
                                painter = painterResource(id = R.drawable.calculate_24),
                                contentDescription = "botao iac",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "Iac",
                            style = MaterialTheme.typography.caption,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedButton(
                            onClick = {
                                navController.navigate(route = Screens.ScreenINFO.route)
                            },
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 10.dp,
                                pressedElevation = 0.dp,
                                focusedElevation = 15.dp,
                                hoveredElevation = 10.dp
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(40.dp),
                                painter = painterResource(id = R.drawable.info_24),
                                contentDescription = "botao info",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "Info", style = MaterialTheme.typography.caption,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedButton(
                            onClick = {
                                navController.navigate(route = Screens.ScreenConfig.route)
                            },
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 10.dp,
                                pressedElevation = 0.dp,
                                focusedElevation = 15.dp,
                                hoveredElevation = 10.dp
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(40.dp),
                                painter = painterResource(id = R.drawable.baseline_settings_24),
                                contentDescription = "botao config",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                        Text(modifier = Modifier.padding(10.dp),
                            text = "Config", style = MaterialTheme.typography.caption,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Anúncio aqui")
            }
        }
}