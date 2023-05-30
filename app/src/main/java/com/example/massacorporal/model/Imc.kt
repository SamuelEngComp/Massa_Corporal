package com.example.massacorporal.model

import java.util.Date

data class Imc(
    val id: Long = 0,
    val valorImc: Float,
    val dataDoRegistro: Date
)
