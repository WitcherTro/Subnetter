package com.example.subnetter.model

import androidx.compose.ui.graphics.Color

data class IpResult(
    val ipAddress: IpAddress,
    val subnetMask: IpAddress,
    val inputs: List<List<String>>,
    val borderColors: List<Color>
)
