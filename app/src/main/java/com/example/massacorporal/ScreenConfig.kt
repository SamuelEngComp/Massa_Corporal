package com.example.massacorporal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.ui.theme.AzulNeve


@Composable
fun ScreenConfig(navController: NavHostController){

    var temaUsuario by rememberSaveable { mutableStateOf("padrao") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Configurações")},
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
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(0.80f)
                                    .padding(10.dp),
                                text = "Personalize o App e deixe-o com a sua cara",
                                style = MaterialTheme.typography.body1)

                            Row( modifier = Modifier
                                    .fillMaxWidth(0.40f)
                                    .padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween)
                            {
                                Text(text = "Padrão", modifier = Modifier
                                    .clickable { temaUsuario = "padrao" }
                                    .align(alignment = Alignment.CenterVertically)
                                )
                                RadioButton(
                                    selected = temaUsuario == "padrao",
                                    onClick = {
                                        temaUsuario = "padrao"
                                    },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = MaterialTheme.colors.primary
                                    )
                                )
                            }

                            Row( modifier = Modifier
                                    .fillMaxWidth(0.40f)
                                    .padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween)
                            {
                                Text(text = "Dark", modifier = Modifier
                                    .clickable { temaUsuario = "dark" }
                                    .align(alignment = Alignment.CenterVertically)
                                )
                                RadioButton(
                                    selected = temaUsuario == "dark",
                                    onClick = {
                                        temaUsuario = "dark"
                                    },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = MaterialTheme.colors.primary
                                    )
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.40f)
                                    .padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween)
                            {
                                Text(
                                    text = "Light", modifier = Modifier
                                        .clickable { temaUsuario = "light" }
                                        .align(alignment = Alignment.CenterVertically)
                                )
                                RadioButton(
                                    selected = temaUsuario == "light",
                                    onClick = {
                                        temaUsuario = "light"
                                    },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = MaterialTheme.colors.primary
                                    )
                                )
                            }

                        }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom)
                    {
                        Text(text = "Anúncio aqui")
                    }
                }
        }
    )
}