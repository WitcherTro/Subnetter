package com.example.subnetter.util

import  com.example.subnetter.model.IpAddress

fun isValidIpAddress(ipAddress: IpAddress): Boolean {
    return ipAddress.octet1 in 0..255 &&
            ipAddress.octet2 in 0..255 &&
            ipAddress.octet3 in 0..255 &&
            ipAddress.octet4 in 0..255
}

fun isValidSubnetMask(subnetMask: IpAddress): Boolean {
    val parts = listOf(subnetMask.octet1, subnetMask.octet2, subnetMask.octet3, subnetMask.octet4)

    var binaryStr = ""
    for (part in parts) {
        if (part !in 0..255) return false
        binaryStr += String.format("%08d", Integer.parseInt(Integer.toBinaryString(part)))
    }

    val firstZero = binaryStr.indexOf('0')
    val lastOne = binaryStr.lastIndexOf('1')

    return firstZero > lastOne
}