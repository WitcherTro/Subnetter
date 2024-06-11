package com.example.subnetter.util

fun isValidIpAddress(ipAddress: List<String>): Boolean {
    return ipAddress.all { it.toIntOrNull() in 0..255 }
}

fun isValidSubnetMask(subnetMask: List<String>): Boolean {
    val binaryString = subnetMask.joinToString("") { it.toIntOrNull()?.toString(2)?.padStart(8, '0') ?: "" }
    val firstZero = binaryString.indexOf('0')
    return firstZero == -1 || binaryString.substring(firstZero).all { it == '0' }
}