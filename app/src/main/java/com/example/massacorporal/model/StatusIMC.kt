package com.example.massacorporal.model

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import com.example.massacorporal.ui.theme.AzulNormal
import com.example.massacorporal.ui.theme.Laranja
import com.example.massacorporal.ui.theme.Vermelho_1
import com.example.massacorporal.ui.theme.Vermelho_2
import com.example.massacorporal.ui.theme.Vermelho_3

enum class StatusIMC(val cor: Color,val nome: String) {

    ABAIXO_DO_PESO(cor = Color.Red, nome = "Magresa"),
    NORMAL(cor = AzulNormal, nome = "Normal"),
    SOBREPESO(cor = Laranja, nome = "Sobrepeso"),
    OBESIDADE_1(cor = Vermelho_1, nome = "Obesidade I"),
    OBESIDADE_2(cor = Vermelho_2, nome = "Obesidade II"),
    OBESIDADE_3(cor = Vermelho_3, nome = "Obesidade III")

}