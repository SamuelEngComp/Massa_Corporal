package com.example.massacorporal

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.ui.theme.AzulNeve
import com.example.massacorporal.util.DataStoreUtil
import com.example.massacorporal.viewmodel.ThemeViewModel
import kotlinx.coroutines.launch


@Composable
fun ScreenConfig(navController: NavHostController, dataStore: DataStoreUtil, themeViewModel: ThemeViewModel){

    //var temaUsuario by rememberSaveable { mutableStateOf("padrao") }

    var switchState by rememberSaveable {themeViewModel.isDarkThemeEnabled }
    val coroutineScope = rememberCoroutineScope()
    //var radioValor by rememberSaveable { mutableStateOf(false) }

    val termosDeUsoUrl = "https://sites.google.com/view/massacorporaldev"
    val intentTermoDeUso = Intent(Intent.ACTION_VIEW, Uri.parse(termosDeUsoUrl))

    val politicaPrivacidadeUrl = "https://sites.google.com/view/massacorporaldevprivacidade/"
    val intentPoliticaPrivacidade = Intent(Intent.ACTION_VIEW, Uri.parse(politicaPrivacidadeUrl))
    val context = LocalContext.current



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Configurações")},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screens.ScreenHome.route){
                                popUpTo(Screens.ScreenHome.route){
                                    inclusive = true
                                }
                            }
                        }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "voltar")
                    }
                },
            elevation = 0.dp,
            backgroundColor = Color.Transparent)
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
                                    elevation = 10.dp
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
                                                checkedThumbColor = MaterialTheme.colors.primary,
                                                uncheckedThumbColor = MaterialTheme.colors.primary
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


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Button(
                                    onClick = {
                                              context.startActivity(intentTermoDeUso)
                                              },
                                    shape = CircleShape,
                                    modifier = Modifier.fillMaxWidth(0.80f),
                                    elevation = ButtonDefaults.elevation(
                                        defaultElevation = 10.dp,
                                        pressedElevation = 0.dp
                                    )
                                ) {
                                    Text(text = "Termos de Uso")
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.open_new),
                                        contentDescription = "Icone Termos de uso",
                                        modifier = Modifier.padding(start = 5.dp))
                                }

                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center)
                            {
                                Button(
                                    onClick = {
                                              context.startActivity(intentPoliticaPrivacidade)
                                              },
                                    modifier = Modifier.fillMaxWidth(0.80f),
                                    elevation = ButtonDefaults.elevation(
                                        defaultElevation = 10.dp,
                                        pressedElevation = 0.dp
                                    ),
                                    shape = CircleShape
                                ) {
                                    Text(text = "Política de Privacidade")
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Icon(painter = painterResource(id = R.drawable.open_new),
                                        contentDescription = "Icone politica privacidade",
                                        modifier = Modifier.padding(start = 5.dp))
                                }
                            }

                        }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom)
                    {
                        Text(text = "Anúncio aqui")
                    }
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