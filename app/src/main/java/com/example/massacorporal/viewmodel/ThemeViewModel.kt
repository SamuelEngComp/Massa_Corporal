package com.example.massacorporal.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.massacorporal.components.Estados
import com.example.massacorporal.model.StatusIMC


class ThemeViewModel: ViewModel() {

    var isDarkThemeEnabled = mutableStateOf(false)

        private set

    fun setTheme(isDarkTheme: Boolean) {
        isDarkThemeEnabled.value = isDarkTheme
    }


    var expandedImc = mutableStateOf(false)
    var expandedIac = mutableStateOf(false)

    var expandedMenuHome = mutableStateOf(false)

    //tela IMC

    var alturaDaPessoa = mutableStateOf("")
    var pesoDaPessoa = mutableStateOf("")

    fun onChangeAltura(valor: String){
        if (valor.length <= 3 && !valor.startsWith("0")) {
            alturaDaPessoa.value = valor
        }
    }

    fun onChangePeso(valor: String){
        if (valor.length <= 5 && !valor.startsWith("0")) {
            pesoDaPessoa.value = valor
        }
    }

    fun showCalcular(): Boolean{
        return !((alturaDaPessoa.value.isEmpty() || alturaDaPessoa.value.equals("0.00") ||
                alturaDaPessoa.value.equals("")) ||
                (pesoDaPessoa.value.isEmpty() || pesoDaPessoa.value.equals("") ||
                        pesoDaPessoa.value.equals("0.00")))
    }


    var resultadoIMC = mutableStateOf(0.0f)
    fun ResultadoDoIMC(altura: Float, peso: Float){
        val resultado = peso / Math.pow(altura.toDouble(), 2.0)
        resultadoIMC.value = resultado.toFloat()
    }
    fun showSalvar(): Boolean{
        return resultadoIMC.value != 0.0f && !((alturaDaPessoa.value.isEmpty() || alturaDaPessoa.value.equals("0.00") ||
                alturaDaPessoa.value.equals("")) ||
                (pesoDaPessoa.value.isEmpty() || pesoDaPessoa.value.equals("") ||
                        pesoDaPessoa.value.equals("0.00")))
    }

    fun CalculoImcStatus(resultadoIMC: Float):String{
        val resultadosPossiveis = listOf(
            StatusIMC.ABAIXO_DO_PESO.nome,
            StatusIMC.NORMAL.nome,
            StatusIMC.SOBREPESO.nome,
            StatusIMC.OBESIDADE_1.nome,
            StatusIMC.OBESIDADE_2.nome,
            StatusIMC.OBESIDADE_3.nome
        )

        var resultado = ""

        when (resultadoIMC) {
            in 0.1f..18.49f -> {
                resultado = resultadosPossiveis[0]
                Estados.estadoImc = resultado
            }

            in 18.50f..24.99f -> {
                resultado = resultadosPossiveis[1]
                Estados.estadoImc = resultado
            }

            in 25.0f..29.99f -> {
                resultado = resultadosPossiveis[2]
                Estados.estadoImc = resultado
            }

            in 30.0f..34.99f -> {
                resultado = resultadosPossiveis[3]
                Estados.estadoImc = resultado
            }

            in 35.0f..39.99f -> {
                resultado = resultadosPossiveis[4]
                Estados.estadoImc = resultado
            }

            in 40.0f..1000.5f -> {
                resultado = resultadosPossiveis[5]
                Estados.estadoImc = resultado
            }
            else -> " "
        }
        return resultado
    }

    fun foregroundIndicatorColorImc(valor: Float): Color {
        when (valor) {
            in 0.1f..18.49f -> {
                return StatusIMC.ABAIXO_DO_PESO.cor
            } //magresa
            in 18.50f..24.99f -> {
                return StatusIMC.NORMAL.cor
            } //normal
            in 25.0f..29.99f -> {
                return StatusIMC.SOBREPESO.cor
            } //sobrepeso
            in 30f..34.99f -> {
                return StatusIMC.OBESIDADE_1.cor
            } //obesidade I
            in 35f..39.99f -> {
                return StatusIMC.OBESIDADE_2.cor
            } //obesidade II
            in 40f..1000.0f -> {
                return StatusIMC.OBESIDADE_3.cor
            } //obesidade III
            else -> return StatusIMC.NORMAL.cor
        }
    }


    fun mensagemFinal(resultadoIMC: Float, altura: Float): String{
        when(CalculoImcStatus(resultadoIMC = resultadoIMC)){
            StatusIMC.ABAIXO_DO_PESO.nome ->
                return "Para ficar Normal é necessário que seu peso seja no mínimo: " +
                        "${String.format("%.2f", (altura * altura) * 21.7f)} Kg"
            StatusIMC.NORMAL.nome ->
                return "Continue assim, mantenha esse peso"
            StatusIMC.SOBREPESO.nome ->
                return "Para ficar Normal é necessário que seu peso seja no mínimo: " +
                        "${String.format("%.2f", (altura * altura) * 24.0f)} Kg"
            StatusIMC.OBESIDADE_1.nome ->
                return "Para ficar Normal é necessário que seu peso seja no mínimo: " +
                        "${String.format("%.2f", (altura * altura) * 24.0f)} Kg"
            StatusIMC.OBESIDADE_2.nome ->
                return "Para ficar Normal é necessário que seu peso seja no mínimo: " +
                        "${String.format("%.2f", (altura * altura) * 24.0f)} Kg"
            StatusIMC.OBESIDADE_3.nome ->
                return "Para ficar Normal é necessário que seu peso seja no mínimo: " +
                        "${String.format("%.2f", (altura * altura) * 24.0f)} Kg"
            else ->
                return " "
        }
    }

    ///////////////////////////////////////////



}