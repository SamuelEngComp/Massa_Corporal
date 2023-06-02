package com.example.massacorporal.model

import java.util.Date

data class Imc(
    var id: Long = 0,
    var valorImc: Float,
    var dataDoRegistro: String,
    var status: StatusIMC
)
