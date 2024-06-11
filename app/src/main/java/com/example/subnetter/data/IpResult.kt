package com.example.subnetter.data

import androidx.compose.ui.graphics.Color

/**
 * Data class representing the result of an IP calculation.
 *
 * @property ipAddress The calculated IP address.
 * @property subnetMask The calculated subnet mask.
 * @property inputs A list of lists of strings representing the network, broadcast, first usable, and last usable IP addresses.
 * @property borderColors A list of colors representing the validation status of each IP address.
 */
data class IpResult(
    val ipAddress: IpAddress,
    val subnetMask: IpAddress,
    val inputs: List<List<String>>,
    val borderColors: List<Color>
)