package com.example.massacorporal.screens.info.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardInfo(
    tituloCard: String,
    animatedState: Float,
    expanded: MutableState<Boolean>,
    imageVector: ImageVector,
    descricaoImgVector: String,
    textoFormulaUtilizada: String
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = CircleShape,
        onClick = {expanded.value = !expanded.value}
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {

            LinhaTituloBotao(
                tituloCard = tituloCard,
                animatedState = animatedState,
                expanded = expanded,
                imageVector = imageVector,
                descricaoImgVector = descricaoImgVector
            )

            ExibirTexto(
                expanded = expanded,
                textoFormulaUtilizada = textoFormulaUtilizada
            )

        }
    }
}

@Composable
fun LinhaTituloBotao(
    tituloCard: String,
    animatedState: Float,
    expanded: MutableState<Boolean>,
    imageVector: ImageVector,
    descricaoImgVector: String){

    Row(verticalAlignment = Alignment.CenterVertically){
        Text(text = tituloCard,
            modifier = Modifier.weight(6f).padding(20.dp),
            fontWeight = FontWeight.Bold)
        IconButton(
            modifier = Modifier
                .alpha(1f)
                .weight(1f)
                .rotate(animatedState),
            onClick = { expanded.value = !expanded.value }
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = descricaoImgVector)
        }
    }
}

@Composable
fun ExibirTexto(
    expanded: MutableState<Boolean>,
    textoFormulaUtilizada: String
){
    if (expanded.value){
        Text(
            modifier = Modifier.padding(20.dp),
            text = textoFormulaUtilizada,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}