package com.example.subnetter.util

import androidx.compose.ui.graphics.Color
import com.example.subnetter.model.IpAddress
import com.example.subnetter.model.IpResult
import com.example.subnetter.model.NetworkInformation
import com.example.subnetter.model.SubnetData

fun handleCalculateClick(
    ipAddress: IpAddress?,
    subnetMask: IpAddress?,
    isCIDR: Boolean,
    cidrValue: Float,
    isValidIpAddress: (IpAddress) -> Boolean,
    isValidSubnetMask: (IpAddress) -> Boolean,
    calculateSubnet: (IpAddress, IpAddress) -> NetworkInformation
): NetworkInformation? {
    // Check if the IP address is not null
    if (ipAddress != null) {
        // Validate the IP address
        if (isValidIpAddress(ipAddress)) {
            var mask: IpAddress
            if (isCIDR) {
                // Calculate the subnet mask from the CIDR value
                mask = SubnetData.fromCidrToIp(cidrValue.toInt())
                // Calculate the subnet information and return it
                return calculateSubnet(ipAddress, mask)
            } else if (subnetMask != null && isValidSubnetMask(subnetMask)) {
                // Calculate the subnet information and return it
                return calculateSubnet(ipAddress, subnetMask)
            }
        }
    }
    // Return null if the IP address is null or invalid
    return null
}

fun handleValidateClick(
    networkInput: List<String>,
    broadcastInput: List<String>,
    firstUsableInput: List<String>,
    lastUsableInput: List<String>,
    networkInfo: NetworkInformation
): List<Color> {
    val networkBorderColor = if (networkInput == networkInfo.networkAddress.toList()) Color.Green else Color.Red
    val broadcastBorderColor = if (broadcastInput == networkInfo.broadcastAddress.toList()) Color.Green else Color.Red
    val firstUsableBorderColor = if (firstUsableInput == networkInfo.firstUsableAddress.toList()) Color.Green else Color.Red
    val lastUsableBorderColor = if (lastUsableInput == networkInfo.lastUsableAddress.toList()) Color.Green else Color.Red

    return listOf(networkBorderColor, broadcastBorderColor, firstUsableBorderColor, lastUsableBorderColor)
}

fun handleSolveClick(networkInfo: NetworkInformation): Pair<List<List<String>>, List<Color>> {
    val networkInput = networkInfo.networkAddress.toList()
    val broadcastInput = networkInfo.broadcastAddress.toList()
    val firstUsableInput = networkInfo.firstUsableAddress.toList()
    val lastUsableInput = networkInfo.lastUsableAddress.toList()

    val networkBorderColor = Color.Green
    val broadcastBorderColor = Color.Green
    val firstUsableBorderColor = Color.Green
    val lastUsableBorderColor = Color.Green

    return Pair(
        listOf(networkInput, broadcastInput, firstUsableInput, lastUsableInput),
        listOf(networkBorderColor, broadcastBorderColor, firstUsableBorderColor, lastUsableBorderColor)
    )
}

fun handleGenerateNewIpClick(): IpResult {
    val ipAddress = generateRandomIpAddress()
    val subnetMask = generateRandomSubnetMask()
    val networkInfo = calculateSubnet(ipAddress, subnetMask)

    val networkInput = listOf("", "", "", "")
    val broadcastInput = listOf("", "", "", "")
    val firstUsableInput = listOf("", "", "", "")
    val lastUsableInput = listOf("", "", "", "")

    val networkBorderColor = Color.Transparent
    val broadcastBorderColor = Color.Transparent
    val firstUsableBorderColor = Color.Transparent
    val lastUsableBorderColor = Color.Transparent

    return IpResult(
        ipAddress,
        subnetMask,
        listOf(networkInput, broadcastInput, firstUsableInput, lastUsableInput),
        listOf(networkBorderColor, broadcastBorderColor, firstUsableBorderColor, lastUsableBorderColor)
    )
}