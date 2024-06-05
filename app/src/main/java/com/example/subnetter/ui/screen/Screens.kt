package com.example.subnetter.ui.screen

sealed class Screens(val route : String) {
    object Calculator : Screens("calculator")
    object Training : Screens("training")
    object Table : Screens("table")
}