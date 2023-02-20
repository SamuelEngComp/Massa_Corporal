package com.example.massacorporal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.massacorporal.components.*
import com.example.massacorporal.navigation.Screens


@Composable
fun ScreenINFO(navController: NavHostController){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Informações")},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screens.ScreenHome.route){
                                popUpTo(Screens.ScreenHome.route){
                                    inclusive = true
                                }
                            }
                        }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "voltar")
                    }
                },
                elevation = 0.dp,
            backgroundColor = Color.Transparent)
        },
        content = {
            paddingValues -> Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {

                        Row(modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            Column() {
                                Text(modifier = Modifier.padding(10.dp), text = "$intuitoApp",
                                    style = MaterialTheme.typography.body1)

                                Text(modifier = Modifier.padding(10.dp), text = "$formulaUtilizadaIMC",
                                    style = MaterialTheme.typography.body1)

                                Text(modifier = Modifier.padding(10.dp), text = "$formulaUtilizadaIAC",
                                    style = MaterialTheme.typography.body1)

                                Text(modifier = Modifier.padding(10.dp), text = "$mensagem",
                                    style = MaterialTheme.typography.body1)

                                /*Text(modifier = Modifier.padding(10.dp), text = "$criadorApp",
                                    style = MaterialTheme.typography.body1)*/
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Anúncio aqui")
                    }
                }
        }
    )
}