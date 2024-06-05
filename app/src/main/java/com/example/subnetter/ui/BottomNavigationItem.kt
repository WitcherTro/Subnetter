package com.example.subnetter.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Default.Home,
    val route : String = ""
) {
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem("Calculator", Icons.Default.Home, "calculator"),
            BottomNavigationItem("Training", Icons.Default.Home, "training"),
            BottomNavigationItem("Table", Icons.Default.Home, "table")
        )
    }
}
