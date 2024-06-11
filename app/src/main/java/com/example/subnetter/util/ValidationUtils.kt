package com.example.subnetter.util

/**
 * Checks if a given IP address is valid.
 *
 * An IP address is valid if all its octets are numbers between 0 and 255.
 *
 * @param ipAddress The IP address to be checked.
 * @return True if the IP address is valid, false otherwise.
 */
fun isValidIpAddress(ipAddress: List<String>): Boolean {
    return ipAddress.all { it.toIntOrNull() in 0..255 }
}

/**
 * Checks if a given subnet mask is valid.
 *
 * A subnet mask is valid if its binary representation does not contain any '1' bits after the first '0' bit.
 *
 * @param subnetMask The subnet mask to be checked.
 * @return True if the subnet mask is valid, false otherwise.
 */
fun isValidSubnetMask(subnetMask: List<String>): Boolean {
    val binaryString = subnetMask.joinToString("") { it.toIntOrNull()?.toString(2)?.padStart(8, '0') ?: "" }
    val firstZero = binaryString.indexOf('0')
    return firstZero == -1 || binaryString.substring(firstZero).all { it == '0' }
}
