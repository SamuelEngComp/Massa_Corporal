package com.example.massacorporal



import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.modifier.modifierLocalConsumer

import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import com.example.massacorporal.components.Datas
import com.example.massacorporal.components.Estados
import com.example.massacorporal.components.Indices

import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.ui.theme.AzulNeve
import com.example.massacorporal.ui.theme.Laranja
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import kotlin.math.roundToInt


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScreenImc(navController: NavHostController){

    /**
     * Variaveis utilizadas para capturar o valor digitado nos campos
     * altura e peso
     */
    var alturaPessoa by rememberSaveable { mutableStateOf("") }
    var pesoPessoa by rememberSaveable { mutableStateOf("") }


    var alturaDaPessoa = 0.0f
    var pesoDaPessoa = 0.0f


    var resultadoIMC by rememberSaveable { mutableStateOf(0.0f) }


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
        targetValue =  resultadoIMC,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    val scope = rememberCoroutineScope()



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
                            scope.launch {
                                resultadoIMC = ResultadoDoImc(alturaDaPessoa, pesoDaPessoa)
                            }
                        },
                        shape = CircleShape,
                        enabled = if (
                            (alturaPessoa.isEmpty() || alturaPessoa.equals("0.00") ||
                                    alturaPessoa.equals("")) ||
                            (pesoPessoa.isEmpty() || pesoPessoa.equals("") ||
                                    pesoPessoa.equals("0.00"))
                        ) {
                            false
                        } else {
                            true
                        }
                    ) {
                        Text(text = " Calcular ")
                    }

                    Button(
                        onClick = {
                            Indices.imc = String.format("%.2f", resultadoIMC)
                            Datas.dataIMC = timestampIMC
                            Estados.controleEstadoIMC = true

                            navController.navigate(Screens.ScreenHome.route){
                                popUpTo(Screens.ScreenHome.route){
                                    inclusive = true
                                }
                            } },
                        enabled = resultadoIMC != 0.0f,
                        shape = CircleShape
                    ) {
                        Text(text = " Salvar ")
                    }
                }

                if (alturaPessoa.isNotEmpty() && pesoPessoa.isNotEmpty()) {
                    alturaDaPessoa = alturaPessoa.toFloat() / 100
                    pesoDaPessoa = pesoPessoa.toFloat() / 100
                }else{
                    resultadoIMC = 0.0f
                }

                CustomComponent(
                    indicatorValue = animatedProgress.toInt(),
                    maxIndicatorValue = 41,
                    bigTextSuffix = "IMC",
                    smallText = CalculoImc(resultadoIMC = animatedProgress),
                    backgroundIndicatorStrokeWidth = 70f,
                    foregroundIndicatorStrokeWidth = 70f,
                    foregroundIndicatorColor = when(animatedProgress){
                        in 0.1f .. 18.5f -> { MaterialTheme.colors.primary } //magresa
                        in 18.6f .. 24.9f -> { MaterialTheme.colors.primary } //normal
                        in 25.0f .. 29.9f -> { Laranja } //sobrepeso
                        in 30f .. 34.9f -> { Color.Red } //obesidade I
                        in 35f .. 39.9f -> { Color.Red } //obesidade II
                        in 40f .. 1000.0f -> { Color.Red } //obesidade III
                        else -> MaterialTheme.colors.primary
                    }
                )

                /**
                 *
                 *

                Graus de obesidade: quais s??o?

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
                Text(text = "An??ncio aqui")
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
            Estados.estadoImc = resultado
        }
        in 18.6f .. 24.9f -> {
            resultado = resultadoPossiveis[1]
            Estados.estadoImc = resultado
        }
        in 25.0f .. 29.9f -> {
            resultado = resultadoPossiveis[2]
            Estados.estadoImc = resultado
        }
        in 30.0f .. 34.9f -> {
            resultado = resultadoPossiveis[3]
            Estados.estadoImc = resultado
        }
        in 35.0f .. 39.9f -> {
            resultado = resultadoPossiveis[4]
            Estados.estadoImc = resultado
        }
        in 40.0f .. 1000.5f -> {
            resultado = resultadoPossiveis[5]
            Estados.estadoImc = resultado
        }
        else -> " "
    }

    return resultado
}


fun ResultadoDoImc(altura: Float, peso: Float): Float{

    val resultado = peso / Math.pow(altura.toDouble(), 2.0)

    return resultado.toFloat()

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

@Composable
fun CustomComponent(
    canvasSize: Dp = 300.dp,
    indicatorValue: Int = 0,
    maxIndicatorValue: Int = 100,
    backgroundIndicatorColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
    backgroundIndicatorStrokeWidth: Float = 100f,
    foregroundIndicatorColor: Color = MaterialTheme.colors.primary,
    foregroundIndicatorStrokeWidth: Float = 100f,
//    indicatorStrokeCap: StrokeCap = StrokeCap.Round,
    bigTextFontSize: TextUnit = MaterialTheme.typography.body2.fontSize,
    bigTextColor: Color = MaterialTheme.colors.onSurface,
    bigTextSuffix: String = "GB",
    smallText: String = "Remaining",
    smallTextFontSize: TextUnit = MaterialTheme.typography.subtitle1.fontSize,
    smallTextColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
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
            MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
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






