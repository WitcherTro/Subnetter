package com.example.subnetter.ui

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.subnetter.R

/**
 * Data class representing a bottom navigation item.
 *
 * @property label The label of the navigation item.
 * @property icon The icon of the navigation item.
 * @property route The route of the navigation item.
 */
data class BottomNavigationItem(
    val label : String = "",
    val icon : Painter,
    val route : String = ""
)

/**
 * Composable function that returns a Painter object for a given drawable resource ID.
 *
 * @param id The drawable resource ID.
 * @return The Painter object.
 */
@Composable
fun getDrawableIcon(@DrawableRes id: Int): Painter {
    return painterResource(id)
}

/**
 * Composable function that returns a list of BottomNavigationItem objects.
 *
 * The list includes items for the Calculator, Training, and Table screens.
 *
 * @return A list of BottomNavigationItem objects.
 */
@Composable
fun bottomNavigationItems() : List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem(stringResource(R.string.menu_calculator), getDrawableIcon(R.drawable.calculate), "calculator"),
        BottomNavigationItem(stringResource(R.string.menu_training), getDrawableIcon(R.drawable.router), "training"),
        BottomNavigationItem(stringResource(R.string.menu_table), getDrawableIcon(R.drawable.table_rows), "table")
    )
}