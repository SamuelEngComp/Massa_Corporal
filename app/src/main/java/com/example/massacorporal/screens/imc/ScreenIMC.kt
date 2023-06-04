package com.example.massacorporal.screens.imc


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import com.example.massacorporal.CurrencyAmountInputVisualTransformation
import com.example.massacorporal.components.*

import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.screens.components.Anuncio
import com.example.massacorporal.screens.components.BarraNavegacaoPadrao
import com.example.massacorporal.ui.theme.Laranja
import com.example.massacorporal.viewmodel.ThemeViewModel
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ScreenImc(navController: NavHostController, model: ThemeViewModel) {

    /**
     * Variavel criada para salvar a hora que o usuario clicou no botao salvar
     */
    val timestampIMC = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
        .format(DateTimeFormatter.ofPattern("dd/MM/yyy"))

    //esconder o teclado quando ocorrer o clique em calcular ou salvar
    val controllerTeclado = LocalSoftwareKeyboardController.current

    /**
     * Animacao para preencher o grafico com base no valor do IMC
     */
    val animatedProgress = animateFloatAsState(
        targetValue = model.resultadoIMC.value,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Scaffold(
        topBar = {
            BarraNavegacaoPadrao(
                titulo = "Calcule seu IMC",
                navController = navController
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                CampoEntradaAltura(
                    valor = model.alturaDaPessoa.value,
                    onChangeValor = { model.onChangeAltura(valor = it) },
                    label = "Altura",
                    trailingIcon = "m "
                )

                CampoEntradaPeso(
                    valor = model.pesoDaPessoa.value,
                    label = "Peso",
                    onChangeValor = {model.onChangePeso(valor = it)},
                    trailingIcon = "Kg "
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.80f)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            model.ResultadoDoIMC(
                                altura = model.alturaDaPessoa.value.toFloat()/100,
                                peso = model.pesoDaPessoa.value.toFloat()/100
                            )
                            controllerTeclado?.hide()
                        },
                        shape = CircleShape,
                        enabled = model.showCalcular()
                    ) {
                        Text(text = " Calcular ")
                    }

                    Button(
                        onClick = {
                            Indices.imc = String.format("%.2f", model.resultadoIMC.value)//resultadoIMC)
                            Datas.dataIMC = timestampIMC
                            Estados.controleEstadoIMC = true

                            navController.navigate(Screens.ScreenHome.route) {
                                popUpTo(Screens.ScreenHome.route) {
                                    inclusive = true
                                }
                            }

                            //reset nos valores
                            model.alturaDaPessoa.value = ""
                            model.pesoDaPessoa.value = ""
                            model.resultadoIMC.value = 0.0f

                        },
                        enabled = model.showSalvar(),//resultadoIMC != 0.0f,
                        shape = CircleShape
                    ) {
                        Text(text = " Salvar ")
                    }
                }

                CustomComponent(
                    indicatorValue = animatedProgress.toInt(),
                    maxIndicatorValue = 41,
                    bigTextSuffix = "IMC",
                    smallText = model.CalculoImcStatus(resultadoIMC = animatedProgress),//CalculoImc(resultadoIMC = animatedProgress),
                    backgroundIndicatorStrokeWidth = 70f,
                    foregroundIndicatorStrokeWidth = 70f,
                    foregroundIndicatorColor = model.foregroundIndicatorColorImc(valor = animatedProgress)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(0.90f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    if (model.showSalvar())
                        CustomTextAlerta(
                            texto = model.mensagemFinal(
                                resultadoIMC = animatedProgress,
                                altura = model.alturaDaPessoa.value.toFloat()/100
                            )
                        )
                }
            }

            Anuncio()

        }
    }
}

@Composable
fun CustomTextAlerta(texto: String) {
    Text(
        text = texto,
        style = MaterialTheme.typography.titleSmall
    )
}


///////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun CustomComponent(
    canvasSize: Dp = 300.dp, //300
    indicatorValue: Int = 0,
    maxIndicatorValue: Int = 100,
    backgroundIndicatorColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    backgroundIndicatorStrokeWidth: Float = 100f,
    foregroundIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    foregroundIndicatorStrokeWidth: Float = 100f,
//    indicatorStrokeCap: StrokeCap = StrokeCap.Round,
    bigTextFontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize,
    bigTextColor: Color = MaterialTheme.colorScheme.onSurface,
    bigTextSuffix: String = "GB",
    smallText: String = "Remaining",
    smallTextFontSize: TextUnit = MaterialTheme.typography.titleSmall.fontSize,
    smallTextColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
) {
    var allowedIndicatorValue by remember {
        mutableStateOf(maxIndicatorValue)
    }
    allowedIndicatorValue = if (indicatorValue <= maxIndicatorValue) {
        indicatorValue
    } else {
        maxIndicatorValue
    }

    var animatedIndicatorValue by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()
    }

    val percentage =
        (animatedIndicatorValue / maxIndicatorValue) * 100

    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(1000)
    )

    val receivedValue by animateIntAsState(
        targetValue = allowedIndicatorValue,
        animationSpec = tween(1000)
    )

    val animatedBigTextColor by animateColorAsState(
        targetValue = if (allowedIndicatorValue == 0)
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        else
            bigTextColor,
        animationSpec = tween(1000)
    )

    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val componentSize = size / 1.25f
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth,
//                    indicatorStokeCap = indicatorStrokeCap
                )
                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    componentSize = componentSize,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
//                    indicatorStokeCap = indicatorStrokeCap
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmbeddedElements(
            bigText = receivedValue,
            bigTextFontSize = bigTextFontSize,
            bigTextColor = animatedBigTextColor,
            bigTextSuffix = bigTextSuffix,
            smallText = smallText,
            smallTextColor = smallTextColor,
            smallTextFontSize = smallTextFontSize
        )
    }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
//    indicatorStokeCap: StrokeCap
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
//    indicatorStokeCap: StrokeCap
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

@Composable
fun EmbeddedElements(
    bigText: Int,
    bigTextFontSize: TextUnit,
    bigTextColor: Color,
    bigTextSuffix: String,
    smallText: String,
    smallTextColor: Color,
    smallTextFontSize: TextUnit
) {
    Text(
        text = smallText,
        color = smallTextColor,
        fontSize = smallTextFontSize,
        textAlign = TextAlign.Center
    )
    Text(
        text = "$bigText ${bigTextSuffix.take(3)}",
        color = bigTextColor,
        fontSize = bigTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoEntradaAltura(
    valor: String,
    onChangeValor: (String) -> Unit,
    label: String,
    trailingIcon: String
){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.80f)
            .padding(10.dp),
        value = valor,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary
        ),
        onValueChange = {
            onChangeValor(it)
        },
        label = { Text(text = label) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Next
        ),
        shape = CircleShape,
        trailingIcon = { Text(text = trailingIcon) },
        visualTransformation = CurrencyAmountInputVisualTransformation()
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CampoEntradaPeso(
    valor: String,
    onChangeValor: (String) -> Unit,
    label: String,
    trailingIcon: String
){
    val controllerTeclado = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.80f)
            .padding(10.dp),
        value = valor,
        onValueChange = {
            onChangeValor(it)
        },
        label = { Text(text = label) },
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
            Text(text = trailingIcon)
        },
        visualTransformation = CurrencyAmountInputVisualTransformation()
    )
}