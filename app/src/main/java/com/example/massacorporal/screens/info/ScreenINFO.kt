package com.example.massacorporal.screens.info

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.massacorporal.components.*
import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.screens.components.Anuncio
import com.example.massacorporal.screens.components.BarraNavegacaoPadrao
import com.example.massacorporal.screens.info.components.CardInfo
import com.example.massacorporal.screens.info.components.ContentFinalInfo

import com.example.massacorporal.screens.info.components.HeaderInfo
import com.example.massacorporal.viewmodel.ThemeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenINFO(navController: NavHostController, model: ThemeViewModel){

    //depois ver uma forma de realizar essa animacao no ThemeViewModel
    val animatedStateIMC by animateFloatAsState(
        targetValue = if (model.expandedImc.value) 180f else 0f)
    val animatedStateIAC by animateFloatAsState(
        targetValue = if (model.expandedIac.value) 180f else 0f)

    Scaffold(
        topBar = {
            BarraNavegacaoPadrao(
                titulo = "Informações",
                navController = navController
            )
        },
        content = {
            paddingValues -> Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {

                        Row(modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                //texto header
                                HeaderInfo()

                                ///////////////////////////////////////////////////////

                                CardInfo(
                                    tituloCard = "Índice de Massa Corporal",
                                    animatedState = animatedStateIMC,
                                    expanded = model.expandedImc,
                                    imageVector = Icons.Default.ArrowDropDown,
                                    descricaoImgVector = "icon drop down",
                                    textoFormulaUtilizada = formulaUtilizadaIMC
                                )

                                CardInfo(
                                    tituloCard = "índice de Adiposidade Corporal",
                                    animatedState = animatedStateIAC,
                                    expanded = model.expandedIac,
                                    imageVector = Icons.Default.ArrowDropDown,
                                    descricaoImgVector = "icon drop down",
                                    textoFormulaUtilizada = formulaUtilizadaIAC
                                )

                                // texto mensagem final
                                ContentFinalInfo()


                            }
                        }

                    }

                    Anuncio()
                }
        }
    )
}
























