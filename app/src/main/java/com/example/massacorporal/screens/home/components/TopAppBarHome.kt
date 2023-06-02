package com.example.massacorporal.screens.home.components


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.massacorporal.R
import com.example.massacorporal.navigation.Screens
import com.example.massacorporal.viewmodel.ThemeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHome(
    titulo: String,
    model: ThemeViewModel,
    navController: NavController
){

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = titulo,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary)
                },
        actions = {
                  IconButton(
                      onClick = {
                          model.expandedMenuHome.value = !model.expandedMenuHome.value
                      },
                  colors = IconButtonDefaults.iconButtonColors(
                      contentColor = MaterialTheme.colorScheme.primary
                  )) {
                      Icon(painter = painterResource(id = R.drawable.more_vert_24), contentDescription = "menu")
                  }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent
        )
    )

    MenuHome(model = model, navController = navController)

}


@Composable
fun MenuHome(model: ThemeViewModel, navController: NavController){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .wrapContentSize(Alignment.TopEnd)
            .background(color = MaterialTheme.colorScheme.primary)
    ){
        DropdownMenu(
            expanded = model.expandedMenuHome.value,
            onDismissRequest = {
                model.expandedMenuHome.value = false
            },
            modifier = Modifier.padding(5.dp)
        ) {
            DropdownMenuItem(
                text = {
                       Text(text = "Informações")
                       },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.info_24),
                        contentDescription = "icon info")
                              },
                onClick = {
                    model.expandedMenuHome.value = false
                    navController.navigate(Screens.ScreenINFO.route)
                }
            )

            DropdownMenuItem(
                text = {
                       Text(text = "Configurações")
                       },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_settings_24),
                        contentDescription = "icon setting")
                              },
                onClick = {
                    model.expandedMenuHome.value = false
                    navController.navigate(Screens.ScreenConfig.route)
                }
            )

        }
    }
}














