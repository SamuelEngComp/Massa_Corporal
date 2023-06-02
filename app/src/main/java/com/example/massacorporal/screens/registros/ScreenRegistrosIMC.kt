package com.example.massacorporal.screens.registros

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.massacorporal.model.Imc
import com.example.massacorporal.model.StatusIMC
import com.example.massacorporal.screens.components.BarraNavegacaoPadrao
import com.example.massacorporal.util.DataStoreUtil
import com.example.massacorporal.viewmodel.ThemeViewModel
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenRegistrosIMC(
    navController: NavHostController,
    dataStore: DataStoreUtil,
    themeViewModel: ThemeViewModel
) {

    var lazyListState = rememberLazyListState()

    val dataIMC = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
        .format(DateTimeFormatter.ofPattern("dd/MM/yyy"))

    val valoresTemporarios = mutableListOf<Imc>(
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.NORMAL),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.NORMAL),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.ABAIXO_DO_PESO),
        Imc(id = 0L, valorImc = 10f, dataDoRegistro = dataIMC, status = StatusIMC.NORMAL),


        )


    Scaffold(
        topBar = {
            BarraNavegacaoPadrao(titulo = "Dados IMC", navController = navController)
                 },
        content = { paddingValues ->
            Column(modifier = Modifier.fillMaxSize().padding(paddingValues = paddingValues)) {

                LazyColumn(modifier = Modifier.weight(1f), state = lazyListState) {
                    items(valoresTemporarios) { valor ->
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "${valor.id}")
                            Text(text = "${valor.valorImc}")
                            Text(text = valor.dataDoRegistro)
                            Text(text = valor.status.nome)
                            Box(
                                modifier = Modifier
                                    .size(5.dp)
                                    .background(color = valor.status.cor)
                            )
                        }
                    }
                }

                Row {
                    Text(text = "Dashboard")
                }
            }

        }
    )
}