package com.example.massacorporal.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.massacorporal.navigation.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraNavegacaoPadrao(
    titulo: String,
    navController: NavHostController
){
    TopAppBar(
        title = { Text(text = titulo) },
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
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}