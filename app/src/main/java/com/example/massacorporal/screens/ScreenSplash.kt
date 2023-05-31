package com.example.massacorporal.screens

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.massacorporal.R
import com.example.massacorporal.navigation.Screens
import kotlinx.coroutines.delay


@Composable
fun ScreenSplash(
    navController: NavHostController)
{

    //iniciando animacao
    var iniciarAnimacao by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if(iniciarAnimacao) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1800, //3000
            easing = LinearOutSlowInEasing
        ))


    LaunchedEffect(key1 = true){
        iniciarAnimacao = true
        delay(1800) //4000
        navController.popBackStack()
        navController.navigate(Screens.ScreenHome.route)
    }

    Splash(alpha = alphaAnim.value)

}




@Composable
fun Splash(alpha: Float){

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(100.dp)
                    .alpha(alpha = alpha),
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .alpha(alpha = alpha),
                text = "Massa Corporal",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
