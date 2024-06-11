package com.example.subnetter.ui.screen

/**
 * Sealed class representing the different screens in the application.
 *
 * @property route The route name of the screen.
 */
sealed class Screens(val route : String) {
    object Calculator : Screens("calculator")
    object Training : Screens("training")
    object Table : Screens("table")
}