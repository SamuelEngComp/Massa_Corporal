package com.example.massacorporal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
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
import com.example.massacorporal.components.Nomes
import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.ui.theme.AzulNeve


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScreenConfig(navController: NavHostController){


    var primeiroNome by remember { mutableStateOf("") }
    var ultimoNome by remember { mutableStateOf("") }

    val controllerTecladoConfig = LocalSoftwareKeyboardController.current


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
                                text = "Personalize o App e deixe-o com a sua cara, insira seu nome e sobrenome",
                                style = MaterialTheme.typography.body1)


                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth(0.80f)
                                    .padding(10.dp),
                                value = primeiroNome,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    cursorColor = AzulNeve,
                                    focusedBorderColor = AzulNeve,
                                    focusedLabelColor = AzulNeve
                                ),
                                onValueChange = {
                                    primeiroNome = it
                                },
                                label = { Text(text = "Nome")},
                                singleLine = true,
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                shape = CircleShape
                            )

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth(0.80f)
                                    .padding(10.dp),
                                value = ultimoNome,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    cursorColor = AzulNeve,
                                    focusedBorderColor = AzulNeve,
                                    focusedLabelColor = AzulNeve
                                ),
                                onValueChange = {
                                    ultimoNome = it
                                },
                                label = { Text(text = "Sobrenome")},
                                singleLine = true,
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done
                                ),
                                shape = CircleShape,
                                keyboardActions = KeyboardActions (
                                    onDone = {controllerTecladoConfig?.hide()}
                                )
                            )


                            Button(
                                modifier = Modifier.fillMaxWidth(0.5f),
                                onClick = {
                                    //CircularProgressIndicator()
                                    controllerTecladoConfig?.hide()
                                    Nomes.nome = primeiroNome
                                    Nomes.sobrenome = ultimoNome
                                    navController.navigate(Screens.ScreenHome.route){
                                        popUpTo(Screens.ScreenHome.route){
                                            inclusive = true
                                        }
                                    }
                                          },
                                shape = CircleShape
                            ) {
                                Text(text = "Salvar")
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