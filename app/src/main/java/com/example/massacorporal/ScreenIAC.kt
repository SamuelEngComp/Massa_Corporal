package com.example.massacorporal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.massacorporal.components.Datas
import com.example.massacorporal.components.Indices
import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.ui.theme.AzulNeve
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.pow


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScreenIAC(navController: NavHostController){

    var sexoUsuario by remember { mutableStateOf("masculino") }

    var larguraQuadril by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }

    var larguraDoQuadril: Float
    var alturaUsuario: Float
    var resultadoIacHomem by remember { mutableStateOf(0.00f) }
    var resultadoIacMulher: Float

    val timestampIAC = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
        .format(DateTimeFormatter.ofPattern("dd/MM/yyy"))

    val controllerTeclado = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
                 TopAppBar(
                     title = { Text(text = "Calcule seu IAC")},
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
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically) {

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Homem",
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .clickable { sexoUsuario = "masculino" })
                                RadioButton(
                                    selected = sexoUsuario == "masculino",
                                    onClick = {
                                        sexoUsuario = "masculino"
                                     },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = MaterialTheme.colors.primary
                                    )
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Mulher",
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .clickable { sexoUsuario = "feminino" }
                                )
                                RadioButton(
                                    selected = sexoUsuario == "feminino",
                                    onClick = {
                                        sexoUsuario = "feminino"
                                    },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = MaterialTheme.colors.primary
                                    )
                                )
                            }
                        }

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(0.80f)
                                .padding(10.dp),
                            value = larguraQuadril,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                cursorColor = MaterialTheme.colors.primary,
                                focusedBorderColor = MaterialTheme.colors.primary,
                                focusedLabelColor = MaterialTheme.colors.primary
                            ),
                            onValueChange = {
                                if (it.length <= 3 && !it.startsWith("0")){
                                    larguraQuadril = it
                                }
                            },
                            label = { Text(text = "Largura Quadril")},
                            singleLine = true,
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.NumberPassword,
                                imeAction = ImeAction.Next
                            ),
                            shape = CircleShape,
                            trailingIcon = {
                                Text(text = "cm ")
                            }
                        )

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(0.80f)
                                .padding(10.dp),
                            value = altura,
                            onValueChange = {
                                if(it.length <= 3 && !it.startsWith("0")){
                                    altura = it
                                }
                            },
                            label = { Text(text = "Altura")},
                            singleLine = true,
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.NumberPassword,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {controllerTeclado?.hide()}
                            ),
                            shape = CircleShape,
                            trailingIcon = {
                                Text(text = "m ")
                            },
                            visualTransformation = CurrencyAmountInputVisualTransformation()
                        )

                        Row(modifier = Modifier
                            .fillMaxWidth(0.80f)
                            .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically )
                        {
                            Button(
                                onClick = {
                                          controllerTeclado?.hide()
                                          },
                                shape = CircleShape
                            ) {
                                Text(text = "Calcular")
                            }

                            Button(
                                onClick = {

                                    Indices.iac = String.format("%.2f", resultadoIacHomem)
                                    Datas.dataIAC = timestampIAC
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


                        if(larguraQuadril.isNotEmpty() && altura.isNotEmpty()){
                            larguraDoQuadril = (larguraQuadril.toFloat())
                            alturaUsuario = (altura.toFloat())/100
                            resultadoIacHomem =
                                (larguraDoQuadril / (alturaUsuario.pow(1.5f))) - 18


                            when(sexoUsuario){
                                "masculino" -> Row {
                                    Column() {
                                        Text(text = "IAC:  $sexoUsuario")
                                        Text(text = "Quadril (cm):  $larguraDoQuadril")
                                        Text(text = "Altura (m):  $alturaUsuario")
                                        Text(text = "Resultado $resultadoIacHomem")
                                    }
                                }
                                "feminino" -> Row {
                                    Column() {
                                        Text(text = "IAC:  $sexoUsuario")
                                        Text(text = "Quadril (cm):  $larguraDoQuadril")
                                        Text(text = "Altura (m):  $alturaUsuario")
                                        Text(text = "Resultado $resultadoIacHomem")
                                    }
                                }
                            }


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.80f)
                                    .padding(10.dp))
                            {

                                Column(modifier = Modifier.fillMaxWidth(0.80f)) {

                                    LinearProgressIndicator(
                                        progress = if (resultadoIacHomem == 0.0f){
                                            0.0f
                                        }else{
                                            resultadoIacHomem/100
                                             },
                                        color = Color.Red,
                                        backgroundColor = Color.White,
                                        modifier = Modifier
                                            .size(width = 100.dp, height = 10.dp)
                                            .border(width = 2.dp, color = MaterialTheme.colors.primary, shape = CircleShape)
                                    )
                                }
                            }
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