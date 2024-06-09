package com.example.subnetter.ui

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.subnetter.R

@Composable
fun getDrawableIcon(@DrawableRes id: Int): Painter {
    return painterResource(id)
}

data class BottomNavigationItem(
    val label : String = "",
    val icon : Painter,
    val route : String = ""
)

@Composable
fun bottomNavigationItems() : List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem("Calculator", getDrawableIcon(R.drawable.calculate), "calculator"),
        BottomNavigationItem("Training", getDrawableIcon(R.drawable.router), "training"),
        BottomNavigationItem("Table", getDrawableIcon(R.drawable.table_rows), "table")
    )
}