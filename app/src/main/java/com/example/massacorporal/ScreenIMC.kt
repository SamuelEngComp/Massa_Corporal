package com.example.massacorporal



import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalSoftwareKeyboardController

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp

import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import com.example.massacorporal.components.Datas
import com.example.massacorporal.components.EstadosIMC
import com.example.massacorporal.components.Indices

import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.ui.theme.AzulNeve
import kotlinx.coroutines.delay
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScreenImc(navController: NavHostController){

    var alturaPessoa by remember { mutableStateOf("") }
    var pesoPessoa by remember { mutableStateOf("") }

    var alturaDaPessoa: Float
    var pesoDaPessoa: Float
    var resultadoIMC by remember { mutableStateOf(0.00f) }

    val timestampIMC = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
        .format(DateTimeFormatter.ofPattern("dd/MM/yyy"))

    val controllerTeclado = LocalSoftwareKeyboardController.current

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Calcule seu IMC")},
                backgroundColor = Color.Transparent,
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
            elevation = 0.dp)
        }
    ) { paddingValues ->
        Column(
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


                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.80f)
                        .padding(10.dp),
                    value = alturaPessoa,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = MaterialTheme.colors.primary,
                        focusedBorderColor = MaterialTheme.colors.primary,
                        focusedLabelColor = MaterialTheme.colors.primary
                    ),
                    onValueChange = {
                        if (it.length <= 3 && !it.startsWith("0")) {
                            alturaPessoa = it
                        }
                    },
                    label = { Text(text = "Altura") },
                    singleLine = true,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next
                    ),
                    shape = CircleShape,
                    trailingIcon = {
                        Text(text = "m ")
                    },
                    visualTransformation = CurrencyAmountInputVisualTransformation()
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.80f)
                        .padding(10.dp),
                    value = pesoPessoa,
                    onValueChange = {
                        if (it.length <= 5 && !it.startsWith("0")) {
                            pesoPessoa = it
                        }
                    },
                    label = { Text(text = "Peso") },
                    singleLine = true,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { controllerTeclado?.hide() }
                    ),
                    shape = CircleShape,
                    trailingIcon = {
                        Text(text = "Kg ")
                    },
                    visualTransformation = CurrencyAmountInputVisualTransformation()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.80f)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            controllerTeclado?.hide()
                        },
                        shape = CircleShape,
                        enabled = if (
                            (alturaPessoa.isEmpty() || alturaPessoa.equals("0.00") || alturaPessoa.equals(
                                ""
                            )) ||
                            (pesoPessoa.isEmpty() || pesoPessoa.equals("") || pesoPessoa.equals("0.00"))
                        ) {
                            false
                        } else {
                            true
                        }
                    ) {
                        Text(text = "Calcular")
                    }

                    Button(
                        onClick = {
                            Indices.imc = String.format("%.2f", resultadoIMC)
                            Datas.dataIMC = timestampIMC

                            navController.navigate(Screens.ScreenHome.route){
                                popUpTo(Screens.ScreenHome.route){
                                    inclusive = true
                                }
                            } },
                        shape = CircleShape
                    ) {
                        Text(text = "Salvar")
                    }
                }

                if (alturaPessoa.isNotEmpty() && pesoPessoa.isNotEmpty()) {

                    alturaDaPessoa = alturaPessoa.toFloat() / 100
                    pesoDaPessoa = pesoPessoa.toFloat() / 100
                    resultadoIMC = (pesoDaPessoa / (alturaDaPessoa * alturaDaPessoa))

                    val res = String.format("%.2f", resultadoIMC)

                    Text(text = res)
                    Text(text = CalculoImc(resultadoIMC))
                    LoadingAnimation3()
                }

                /**
                 *
                 *

                Graus de obesidade: quais são?

                IMC abaixo de 18,5: abaixo do peso.
                IMC entre 18,5 e 24,9: peso normal.
                IMC entre 25 e 29,9: sobrepeso.
                IMC entre 30 e 34,9: obesidade grau I.
                IMC entre 35 e 39,9: obesidade grau II.
                IMC acima de 40: obesidade grau III.

                 *
                 *
                 */


            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(text = "Anúncio aqui")
            }

        }
    }
}


fun CalculoImc(resultadoIMC: Float): String {

    val resultadoPossiveis = listOf("Abaixo do Peso", "Normal", "Sobrepeso", "Obesidade Grau I", "Obesidade Grau II", "Obesidade Grau III")
    var resultado = ""

    when(resultadoIMC){
        in 0.1f .. 18.5f -> {
            resultado = resultadoPossiveis[0]
            EstadosIMC.estadoImc = resultado
        }
        in 18.6f .. 24.9f -> {
            resultado = resultadoPossiveis[1]
            EstadosIMC.estadoImc = resultado
        }
        in 25.0f .. 29.9f -> {
            resultado = resultadoPossiveis[2]
            EstadosIMC.estadoImc = resultado
        }
        in 30.0f .. 34.9f -> {
            resultado = resultadoPossiveis[3]
            EstadosIMC.estadoImc = resultado
        }
        in 35.0f .. 39.9f -> {
            resultado = resultadoPossiveis[4]
            EstadosIMC.estadoImc = resultado
        }
        in 40.0f .. 1000.5f -> {
            resultado = resultadoPossiveis[5]
            EstadosIMC.estadoImc = resultado
        }
        else -> " "
    }

    return resultado
}




///////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun LoadingAnimation3(
    circleColor: Color = MaterialTheme.colors.primary,//Color(0xFF35898F),
    circleSize: Dp = 36.dp,
    animationDelay: Int = 400,
    initialAlpha: Float = 0.3f
) {

    // 3 circles
    val circles = listOf(
        remember {
            Animatable(initialValue = initialAlpha)
        },
        remember {
            Animatable(initialValue = initialAlpha)
        },
        remember {
            Animatable(initialValue = initialAlpha)
        }
    )

    circles.forEachIndexed { index, animatable ->

        LaunchedEffect(Unit) {

            // Use coroutine delay to sync animations
            delay(timeMillis = (animationDelay / circles.size).toLong() * index)

            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDelay
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }

    // container for circles
    Row(
        modifier = Modifier
        //.border(width = 2.dp, color = Color.Magenta)
    ) {

        // adding each circle
        circles.forEachIndexed { index, animatable ->

            // gap between the circles
            if (index != 0) {
                Spacer(modifier = Modifier.width(width = 6.dp))
            }

            Box(
                modifier = Modifier
                    .size(size = circleSize)
                    .clip(shape = CircleShape)
                    .background(
                        color = circleColor
                            .copy(alpha = animatable.value)
                    )
            ) {
            }
        }
    }
}




///////////////////////////////////////////////////////////////////////////////////////////////////








