package com.example.subnetter.ui

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.subnetter.R

data class BottomNavigationItem(
    val label : String = "",
    val icon : Painter,
    val route : String = ""
)

@Composable
fun getDrawableIcon(@DrawableRes id: Int): Painter {
    return painterResource(id)
}

@Composable
fun bottomNavigationItems() : List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem(stringResource(R.string.menu_calculator), getDrawableIcon(R.drawable.calculate), "calculator"),
        BottomNavigationItem(stringResource(R.string.menu_training), getDrawableIcon(R.drawable.router), "training"),
        BottomNavigationItem(stringResource(R.string.menu_table), getDrawableIcon(R.drawable.table_rows), "table")
    )
}