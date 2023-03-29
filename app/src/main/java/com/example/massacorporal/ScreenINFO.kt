package com.example.massacorporal

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.massacorporal.components.*
import com.example.massacorporal.navigation.Screens


@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun ScreenINFO(navController: NavHostController){


    var expandedIMC by remember { mutableStateOf(false) }
    val animatedStateIMC by animateFloatAsState(
        targetValue = if (expandedIMC) 180f else 0f)

    var expandedIAC by remember { mutableStateOf(false) }
    val animatedStateIAC by animateFloatAsState(
        targetValue = if (expandedIAC) 180f else 0f)

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
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
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
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(modifier = Modifier.padding(10.dp), text = intuitoApp,
                                    style = MaterialTheme.typography.body1)

                                ///////////////////////////////////////////////////////

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                        .animateContentSize(
                                            animationSpec = tween(
                                                durationMillis = 300,
                                                easing = FastOutLinearInEasing
                                            )
                                        ),
                                    shape = RoundedCornerShape(10.dp),
                                    onClick = {expandedIMC = !expandedIMC}) {
                                    Column(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically){
                                            Text(text = "Índice de Massa Corporal",
                                                modifier = Modifier.weight(6f),
                                                fontWeight = FontWeight.Bold)
                                            IconButton(
                                                modifier = Modifier
                                                    .alpha(ContentAlpha.medium)
                                                    .weight(1f)
                                                    .rotate(animatedStateIMC),
                                                onClick = { expandedIMC = !expandedIMC }
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.ArrowDropDown,
                                                    contentDescription = "icon drop down")
                                            }
                                        }

                                        if (expandedIMC){
                                            Text(modifier = Modifier.padding(10.dp), text = formulaUtilizadaIMC,
                                                style = MaterialTheme.typography.body1)
                                        }

                                    }
                                }

                                ////////////////////////////////////////////////////////

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                        .animateContentSize(
                                            animationSpec = tween(
                                                durationMillis = 300,
                                                easing = LinearEasing
                                            )
                                        ),
                                    shape = RoundedCornerShape(10.dp),
                                    onClick = {expandedIAC = !expandedIAC}) {
                                    Column(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically){
                                            Text(text = "índice de Adiposidade Corporal",
                                                modifier = Modifier.weight(6f),
                                            fontWeight = FontWeight.Bold)
                                            IconButton(
                                                modifier = Modifier
                                                    .alpha(ContentAlpha.medium)
                                                    .weight(1f)
                                                    .rotate(animatedStateIAC),
                                                onClick = { expandedIAC = !expandedIAC }
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.ArrowDropDown,
                                                    contentDescription = "icon drop down")
                                            }
                                        }

                                        if (expandedIAC){
                                            Text(modifier = Modifier.padding(10.dp),
                                                text = "$formulaUtilizadaIAC $comoMedirIAC",
                                                style = MaterialTheme.typography.body1)
                                        }

                                    }
                                }



                                ////////////////////////////////////////////////////////


                                Text(modifier = Modifier.padding(10.dp), text = mensagem,
                                    style = MaterialTheme.typography.body1)

                                /*Text(modifier = Modifier.padding(10.dp), text = observacaoCrianca,
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