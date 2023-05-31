package com.example.massacorporal.screens.info.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.massacorporal.components.mensagem

@Composable
fun ContentFinalInfo(){
    Text(
        modifier = Modifier.padding(10.dp),
        text = mensagem,
        style = MaterialTheme.typography.bodyMedium
    )
}