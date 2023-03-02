package com.example.massacorporal

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.ui.theme.AzulNeve
import com.example.massacorporal.ui.theme.BrancoNeve
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
            durationMillis = 3000, //3000
            easing = LinearOutSlowInEasing
        ))


    LaunchedEffect(key1 = true){
        iniciarAnimacao = true
        delay(4000) //4000
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
                    color = MaterialTheme.colors.primary
                )
            )
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .alpha(alpha = alpha),
                text = "Massa Corporal",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary
            )
        }
    }
}
