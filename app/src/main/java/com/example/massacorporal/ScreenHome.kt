package com.example.massacorporal

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.ui.theme.AzulNeve


@Composable
fun ScreenHome(navController: NavHostController){

    val saudacao = "Olá, "
    val usuarioFulano = "Fulano de Tal"

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

                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(text = saudacao,
                            style = MaterialTheme.typography.subtitle1)
                        Text(text = usuarioFulano,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold)
                    }

                    Column(modifier = Modifier.padding(10.dp)) {
                        Box(modifier = Modifier
                            .border(width = 2.dp, color = Color.White,
                                shape = CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.person_24),
                                contentDescription = "Imagem Perfil",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .background(color = Color.Transparent)
                                    .size(100.dp)
                                    .clip(CircleShape),
                                colorFilter = ColorFilter.tint(color = AzulNeve)
                            )
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
                            Column {
                                Text(text = " 34 ", style = MaterialTheme.typography.h2,
                                    fontWeight = FontWeight.Bold)
                            }
                            Column{
                                Text(text = " IMC ", style = MaterialTheme.typography.h2,
                                    modifier = Modifier
                                        .background(
                                            color = MaterialTheme.colors.background,
                                            shape = CircleShape)
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
                            Column {
                                Text(
                                    text = " IAC ",
                                    style = MaterialTheme.typography.h2,
                                    modifier = Modifier
                                        .background(
                                            color = MaterialTheme.colors.background,
                                            shape = CircleShape)
                                        .padding(10.dp)
                                )
                            }
                            Column{
                                Text(
                                    text = " 21 ",
                                    style = MaterialTheme.typography.h2,
                                    fontWeight = FontWeight.Bold)
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
                                tint = AzulNeve
                            )
                        }
                        Text(modifier = Modifier.padding(10.dp),
                            text = "IMC", style = MaterialTheme.typography.caption)

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
                                tint = AzulNeve
                            )
                        }
                        Text(modifier = Modifier.padding(10.dp),text = "IAC",
                            style = MaterialTheme.typography.caption)
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
                                tint = AzulNeve
                            )
                        }
                        Text(modifier = Modifier.padding(10.dp),
                            text = "INFO", style = MaterialTheme.typography.caption)
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
                                tint = AzulNeve
                            )
                        }
                        Text(modifier = Modifier.padding(10.dp),
                            text = "CONFIG", style = MaterialTheme.typography.caption)
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