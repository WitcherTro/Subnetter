package com.example.subnetter.util

import androidx.compose.ui.graphics.Color
import com.example.subnetter.model.IpAddress
import com.example.subnetter.model.IpResult
import com.example.subnetter.model.NetworkInformation
import com.example.subnetter.model.SubnetData

/**
 * Handles the click event for the calculate button.
 * Validates the IP address and subnet mask, then calculates the subnet information.
 *
 * @param ipAddress The IP address as a list of strings.
 * @param subnetMask The subnet mask as a list of strings.
 * @param isCIDR A boolean indicating whether the subnet mask is in CIDR notation.
 * @param cidrValue The CIDR value as a float.
 * @param isValidIpAddress A function that validates the IP address.
 * @param isValidSubnetMask A function that validates the subnet mask.
 * @param calculateSubnet A function that calculates the subnet information.
 * @return The calculated subnet information, or null if the IP address or subnet mask is invalid.
 */
fun handleCalculateClick(
    ipAddress: List<String>?,
    subnetMask: List<String>?,
    isCIDR: Boolean,
    cidrValue: Float,
    isValidIpAddress: (List<String>) -> Boolean,
    isValidSubnetMask: (List<String>) -> Boolean,
    calculateSubnet: (IpAddress, IpAddress) -> NetworkInformation
): NetworkInformation? {
    // Check if the IP address and subnet mask is not null
    if (ipAddress != null && ipAddress.size == 4 && subnetMask != null && subnetMask.size == 4) {
        // Validate the IP address
        if (isValidIpAddress(ipAddress)) {
            val mask: IpAddress
            if (isCIDR) {
                // Calculate the subnet mask from the CIDR value
                mask = SubnetData.fromCidrToIp(cidrValue.toInt())
                // Calculate the subnet information and return it
                return calculateSubnet(
                    IpAddress(ipAddress[0].toInt(), ipAddress[1].toInt(), ipAddress[2].toInt(), ipAddress[3].toInt()),
                    mask
                )
            } else if (subnetMask != null && subnetMask.size == 4 && isValidSubnetMask(subnetMask)) {
                // Calculate the subnet information and return it
                return calculateSubnet(
                    IpAddress(ipAddress[0].toInt(), ipAddress[1].toInt(), ipAddress[2].toInt(), ipAddress[3].toInt()),
                    IpAddress(subnetMask[0].toInt(), subnetMask[1].toInt(), subnetMask[2].toInt(), subnetMask[3].toInt())
                )
            }
        }
    }
    // Return null if the IP address is null or invalid
    return null
}

/**
 * Handles the click event for the validate button.
 * Validates the network, broadcast, first usable, and last usable IP addresses.
 *
 * @param networkInput The network IP address as a list of strings.
 * @param broadcastInput The broadcast IP address as a list of strings.
 * @param firstUsableInput The first usable IP address as a list of strings.
 * @param lastUsableInput The last usable IP address as a list of strings.
 * @param networkInfo The network information.
 * @return A list of colors representing the validation status of each IP address.
 */
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

/**
 * Handles the click event for the solve button.
 * Solves for the network, broadcast, first usable, and last usable IP addresses.
 *
 * @param networkInfo The network information.
 * @return A pair of lists. The first list contains the solved IP addresses. The second list contains the validation status of each IP address.
 */
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

/**
 * Handles the click event for the generate new IP button.
 * Generates a new IP address and subnet mask.
 *
 * @return An IpResult object containing the new IP address and subnet mask, and the initial values for the network, broadcast, first usable, and last usable IP addresses.
 */
fun handleGenerateNewIpClick(): IpResult {
    val ipAddress = generateRandomIpAddress()
    val subnetMask = generateRandomSubnetMask()

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

/**
 * Handles the click event for the validate button when the subnet mask is in CIDR notation.
 * Validates the network, broadcast, first usable, and last usable IP addresses.
 *
 * @param networkInput The network IP address as a list of strings.
 * @param broadcastInput The broadcast IP address as a list of strings.
 * @param firstUsableInput The first usable IP address as a list of strings.
 * @param lastUsableInput The last usable IP address as a list of strings.
 * @param networkInformation The network information.
 * @return A list of colors representing the validation status of each IP address.
 */
fun handleValidateCidrClick(
    networkInput: List<String>,
    broadcastInput: List<String>,
    firstUsableInput: List<String>,
    lastUsableInput: List<String>,
    networkInformation: NetworkInformation): List<Color> {
    val networkBorderColor = if (networkInput == networkInformation.networkAddress.toList()) Color.Green else Color.Red
    val broadcastBorderColor = if (broadcastInput == networkInformation.broadcastAddress.toList()) Color.Green else Color.Red
    val firstUsableBorderColor = if (firstUsableInput == networkInformation.firstUsableAddress.toList()) Color.Green else Color.Red
    val lastUsableBorderColor = if (lastUsableInput == networkInformation.lastUsableAddress.toList()) Color.Green else Color.Red

    return listOf(networkBorderColor, broadcastBorderColor, firstUsableBorderColor, lastUsableBorderColor)
}

/**
 * Handles the click event for the solve button when the subnet mask is in CIDR notation.
 * Solves for the network, broadcast, first usable, and last usable IP addresses.
 *
 * @param networkInformation The network information.
 * @return A pair of lists. The first list contains the solved IP addresses. The second list contains the validation status of each IP address.
 */
fun handleSolveCidrClick(
    networkInformation: NetworkInformation): Pair<List<List<String>>, List<Color>> {
    val networkInput = networkInformation.networkAddress.toList()
    val broadcastInput = networkInformation.broadcastAddress.toList()
    val firstUsableInput = networkInformation.firstUsableAddress.toList()
    val lastUsableInput = networkInformation.lastUsableAddress.toList()

    val networkBorderColor = Color.Green
    val broadcastBorderColor = Color.Green
    val firstUsableBorderColor = Color.Green
    val lastUsableBorderColor = Color.Green

    return Pair(
        listOf(networkInput, broadcastInput, firstUsableInput, lastUsableInput),
        listOf(networkBorderColor, broadcastBorderColor, firstUsableBorderColor, lastUsableBorderColor)
    )
}