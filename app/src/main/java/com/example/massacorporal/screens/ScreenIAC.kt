package com.example.massacorporal.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.massacorporal.CurrencyAmountInputVisualTransformation
import com.example.massacorporal.components.Datas
import com.example.massacorporal.components.Estados
import com.example.massacorporal.components.Indices
import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.screens.components.Anuncio
import com.example.massacorporal.screens.components.BarraNavegacaoPadrao
import com.example.massacorporal.screens.imc.CustomComponent
import com.example.massacorporal.screens.imc.CustomTextAlerta
import com.example.massacorporal.ui.theme.Laranja
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.pow


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ScreenIAC(navController: NavHostController){

    var sexoUsuario by rememberSaveable { mutableStateOf("masculino") }

    var larguraQuadril by rememberSaveable { mutableStateOf("") }
    var altura by rememberSaveable { mutableStateOf("") }

    var larguraDoQuadril = 0.0f
    var alturaUsuario = 0.0f
    var resultadoIac by rememberSaveable { mutableStateOf(0.00f) }

    val timestampIAC = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
        .format(DateTimeFormatter.ofPattern("dd/MM/yyy"))

    val controllerTeclado = LocalSoftwareKeyboardController.current


    val animatedProgress = animateFloatAsState(
        targetValue =  resultadoIac,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value


    val scope = rememberCoroutineScope()


    Scaffold(
        topBar = {
            BarraNavegacaoPadrao(
                titulo = "Calcule seu IAC",
                navController = navController
            )
        },
        content = {
            paddingValues -> Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState()),
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
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically) {

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Homem",
                                    modifier = Modifier
                                        .clickable { sexoUsuario = "masculino" })
                                RadioButton(
                                    selected = sexoUsuario == "masculino",
                                    onClick = {
                                        sexoUsuario = "masculino"
                                     },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = MaterialTheme.colorScheme.primary
                                    )
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Mulher",
                                    modifier = Modifier
                                        .clickable { sexoUsuario = "feminino" }
                                )
                                RadioButton(
                                    selected = sexoUsuario == "feminino",
                                    onClick = {
                                        sexoUsuario = "feminino"
                                    },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = MaterialTheme.colorScheme.primary
                                    )
                                )
                            }
                        }

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(0.80f)
                                .padding(5.dp),
                            value = larguraQuadril,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                cursorColor = MaterialTheme.colorScheme.primary,
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                focusedLabelColor = MaterialTheme.colorScheme.primary
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
                                .padding(5.dp),
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
                                enabled = if (
                                    (larguraQuadril.isEmpty() || larguraQuadril.equals("0.00") ||
                                            larguraQuadril.equals("")) ||
                                    (altura.isEmpty() || altura.equals("0.00") || altura.equals("")))
                                {
                                    false
                                }else{
                                    true
                                     },
                                onClick = {
                                    controllerTeclado?.hide()
                                    scope.launch {
                                        resultadoIac = ResultadoDoIac(larguraDoQuadril, alturaUsuario)
                                    }
                                          },
                                shape = CircleShape
                            ) {
                                Text(text = " Calcular ")
                            }

                            Button(
                                onClick = {
                                    Indices.iac = String.format("%.2f", resultadoIac)
                                    Datas.dataIAC = timestampIAC
                                    Estados.controleEstadoIAC = true
                                    navController.navigate(Screens.ScreenHome.route){
                                        popUpTo(Screens.ScreenHome.route){
                                            inclusive = true
                                        }
                                    }
                                          },
                                enabled = resultadoIac != 0.0f,
                                shape = CircleShape
                            ) {
                                Text(text = " Salvar ")
                            }
                        }

                        if(larguraQuadril.isNotEmpty() && altura.isNotEmpty()){
                            larguraDoQuadril = (larguraQuadril.toFloat())
                            alturaUsuario = (altura.toFloat())/100
                        }else{
                            resultadoIac = 0.0f
                        }

                        CustomComponent(
                            indicatorValue = animatedProgress.toInt(),
                            maxIndicatorValue = 41,
                            bigTextSuffix = "IAC",
                            smallText = CalculoIac(resultadoDoIac = animatedProgress, sexo = sexoUsuario),
                            backgroundIndicatorStrokeWidth = 70f,
                            foregroundIndicatorStrokeWidth = 70f,
                            foregroundIndicatorColor = if (sexoUsuario == "masculino"){
                                when(animatedProgress){
                                    in 6.0f .. 11.0f -> { Color.Red } //muito baixa
                                    in 11.1f .. 15.0f -> { Laranja } //baixa
                                    in 15.1f .. 19.0f -> { Color.Green } //ideal
                                    in 19.1f .. 25.0f -> { MaterialTheme.colorScheme.primary } //moderada
                                    in 25.1f .. 1029.9f -> { Color.Red } //excesso
                                    else -> MaterialTheme.colorScheme.primary
                                }
                            }else{
                                when(animatedProgress){
                                    in 10.0f .. 16.0f -> { Color.Red } //muito baixa
                                    in 16.1f .. 20.0f -> { Laranja } //baixa
                                    in 20.1f .. 26.0f -> { Color.Green } //ideal
                                    in 26.1f .. 30.0f -> { MaterialTheme.colorScheme.primary } //moderada
                                    in 30.1f .. 1029.9f -> { Color.Red } //excesso
                                    else -> MaterialTheme.colorScheme.primary
                                }
                            }

                        )

                        Row(modifier = Modifier.fillMaxWidth(0.90f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center) {


                            if (sexoUsuario == "masculino"){
                                when(CalculoIac(resultadoDoIac = animatedProgress, sexo = sexoUsuario)){
                                    "Muito baixa" -> {
                                        CustomTextAlerta(texto = "Para ficar Normal é necessário que seu quadril seja no mínimo: " +
                                                "${String.format("%.2f",( (alturaUsuario.pow(1.5f) * (19.0f + 18f) )))} cm")
                                    }
                                    "Baixa" -> {
                                        CustomTextAlerta(texto = "Para ficar Normal é necessário que seu quadril seja no mínimo: " +
                                                "${String.format("%.2f",( (alturaUsuario.pow(1.5f) * (19.0f + 18f) )))} cm")
                                    }
                                    "Ideal" -> {
                                        CustomTextAlerta(texto = "Parabéns, agora é só manter " )
                                    }
                                    "Moderada" -> {
                                        CustomTextAlerta(texto = "Para ficar Normal é necessário que seu quadril seja no mínimo: " +
                                                "${String.format("%.2f",( (alturaUsuario.pow(1.5f) * (19.0f + 18f) )))} cm")
                                    }
                                    "Excesso" -> {
                                        CustomTextAlerta(texto = "Para ficar Normal é necessário que seu quadril seja no mínimo: " +
                                                "${String.format("%.2f",( (alturaUsuario.pow(1.5f) * (19.0f + 18f) )))} cm")
                                    }
                                    else -> {""}
                                }
                            }else{
                                when(CalculoIac(resultadoDoIac = animatedProgress, sexo = sexoUsuario)){
                                    "Muito baixa" -> {
                                        CustomTextAlerta(texto = "Para ficar Normal é necessário que seu quadril seja no mínimo: " +
                                                "${String.format("%.2f",( (alturaUsuario.pow(1.5f) * (21.0f + 18f) )))} cm")
                                    }
                                    "Baixa" -> {
                                        CustomTextAlerta(texto = "Para ficar Normal é necessário que seu quadril seja no mínimo: " +
                                                "${String.format("%.2f",( (alturaUsuario.pow(1.5f) * (21.0f + 18f) )))} cm")
                                    }
                                    "Ideal" -> {
                                        CustomTextAlerta(texto = "Parabéns, agora é só manter " )
                                    }
                                    "Moderada" -> {
                                        CustomTextAlerta(texto = "Para ficar Normal é necessário que seu quadril seja no mínimo: " +
                                                "${String.format("%.2f",( (alturaUsuario.pow(1.5f) * (23.0f + 18f) )))} cm")
                                    }
                                    "Excesso" -> {
                                        CustomTextAlerta(texto = "Para ficar Normal é necessário que seu quadril seja no mínimo: " +
                                                "${String.format("%.2f",( (alturaUsuario.pow(1.5f) * (23.0f + 18f) )))} cm")
                                    }
                                    else -> {""}
                                }
                            }




                        }




                    }

                        Anuncio()

                }
        }
    )
}


fun ResultadoDoIac(larguraDoQuadril: Float, alturaDoUsuario: Float): Float {
    return (larguraDoQuadril / (alturaDoUsuario.pow(1.5f))) - 18
}


/**
 * Calculo IAC -
 *
 * HOMEM:
 * Normal = 8 .. 20
 * Sobrepeso = 21 .. 25
 * Obeso = > 25
 *
 * MULHER:
 * Normal = 21 .. 32
 * Sobrepeso = 33 .. 38
 * Obeso = > 38
 *
 *
 *
 */
fun CalculoIac(resultadoDoIac: Float, sexo: String): String{

    val resultadosDoIAC = listOf("Muito baixa","Baixa","Ideal","Moderada","Excesso")
    var resultado = ""

    if (sexo == "masculino"){
        when(resultadoDoIac){
            in 6.0f .. 11.0f -> {
                resultado = resultadosDoIAC[0]
                Estados.estadoIac = resultado
            }
            in 11.1f .. 15.0f -> {
                resultado = resultadosDoIAC[1]
                Estados.estadoIac = resultado
            }
            in 15.1f .. 19.0f -> {
                resultado = resultadosDoIAC[2]
                Estados.estadoIac = resultado
            }
            in 19.1f .. 25.0f -> {
                resultado = resultadosDoIAC[3]
                Estados.estadoIac = resultado
            }
            in 25.1f .. 1019.0f -> {
                resultado = resultadosDoIAC[4]
                Estados.estadoIac = resultado
            }
            else -> ""
        }
    }else{
        when(resultadoDoIac){
            in 10.0f .. 16.0f -> {
                resultado = resultadosDoIAC[0]
                Estados.estadoIac = resultado
            }
            in 16.1f .. 20.0f -> {
                resultado = resultadosDoIAC[1]
                Estados.estadoIac = resultado
            }
            in 20.1f .. 26.0f -> {
                resultado = resultadosDoIAC[2]
                Estados.estadoIac = resultado
            }
            in 26.1f .. 30.0f -> {
                resultado = resultadosDoIAC[3]
                Estados.estadoIac = resultado
            }
            in 30.1f .. 1000.0f -> {
                resultado = resultadosDoIAC[4]
                Estados.estadoIac = resultado
            }
            else -> ""
        }
    }

    return resultado
}