package com.example.massacorporal.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.massacorporal.R

import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.screens.components.Anuncio
import com.example.massacorporal.screens.components.BarraNavegacaoPadrao
import com.example.massacorporal.util.DataStoreUtil
import com.example.massacorporal.viewmodel.ThemeViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenConfig(navController: NavHostController, dataStore: DataStoreUtil, themeViewModel: ThemeViewModel){

    var switchState by rememberSaveable {themeViewModel.isDarkThemeEnabled }
    val coroutineScope = rememberCoroutineScope()

    val termosDeUsoUrl = "https://sites.google.com/view/massacorporaldev"
    val intentTermoDeUso = Intent(Intent.ACTION_VIEW, Uri.parse(termosDeUsoUrl))

    val politicaPrivacidadeUrl = "https://sites.google.com/view/massacorporaldevprivacidade/"
    val intentPoliticaPrivacidade = Intent(Intent.ACTION_VIEW, Uri.parse(politicaPrivacidadeUrl))
    val context = LocalContext.current



    Scaffold(
        topBar = {
            BarraNavegacaoPadrao(
                titulo = "Configurações",
                navController = navController
            )
        },
        content = {
                paddingValues -> Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                            ) {

                                Card(
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = 10.dp
                                    )
                                ) {

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically) {

                                        Text(
                                            text = if (switchState) "Dark mode" else "Light mode",
                                            modifier = Modifier
                                                .align(alignment = Alignment.CenterVertically)
                                                .padding(start = 10.dp)
                                        )

                                        Switch(
                                            checked = switchState,
                                            colors = SwitchDefaults.colors(
                                                checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                                                uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                                                uncheckedBorderColor = MaterialTheme.colorScheme.primary
                                            ),
                                            onCheckedChange = {
                                                switchState = it

                                                coroutineScope.launch {
                                                    dataStore.saveTheme(it)
                                                }
                                            }
                                        )
                                    }
                                }
                            }


                            ButtomTermos(
                                contexto = context,
                                intent = intentTermoDeUso,
                                textoBotao = "Termos de Uso",
                                icon = R.drawable.open_new,
                                descricao = "Icone Termos de uso"
                            )

                            ButtomTermos(
                                contexto = context,
                                intent = intentPoliticaPrivacidade,
                                textoBotao = "Política de Privacidade",
                                icon = R.drawable.open_new,
                                descricao = "Icone politica privacidade"
                            )
                        }

                        Anuncio()
                }
        }
    )


    /**
     * forma muito simples e bacana de fazer as alterações de cores do sistema utilizando jetpack compose
     * utilizou o padrao ViewModel -> MVVM que eu tenho que aprender o quanto antes...
     *
     * fonte: https://itnext.io/dark-theme-in-jetpack-compose-with-material-3-757e45118259
     *
     */

}

@Composable
fun ButtomTermos(contexto: Context, intent: Intent, textoBotao: String, icon: Int, descricao: String){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                contexto.startActivity(intent)
            },
            shape = CircleShape,
            modifier = Modifier.fillMaxWidth(0.80f),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp
            )
        ) {
            Text(text = textoBotao)
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(id = icon),
                contentDescription = descricao,
                modifier = Modifier.padding(start = 5.dp))
        }

    }

}




