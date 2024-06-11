package com.example.subnetter.util

import com.example.subnetter.model.IpAddress
import com.example.subnetter.model.NetworkInformation

fun calculateSubnet(ipAddress: IpAddress, subnetMask: IpAddress): NetworkInformation {
    // Convert the IP address and subnet mask to binary
    val ipBinary = ipAddress.toBinary()
    val maskBinary = subnetMask.toBinary()

    // Calculate the network address by performing a bitwise AND operation on the IP address and subnet mask
    val networkBinary = ipBinary and maskBinary
    val networkAddress = IpAddress.fromBinary(networkBinary)

    // Calculate the broadcast address by performing a bitwise OR operation on the network address and the inverted subnet mask
    val broadcastBinary = networkBinary or maskBinary.inv()
    val broadcastAddress = IpAddress.fromBinary(broadcastBinary)

    // The first usable address is the network address plus one
    val firstUsableAddress = IpAddress.fromBinary(networkBinary + 1)

    // The last usable address is the broadcast address minus one
    val lastUsableAddress = IpAddress.fromBinary(broadcastBinary - 1)

    // The number of hosts is 2^(32 - subnet bits) - 2
    val subnetBits = Integer.bitCount(maskBinary)
    val numberOfHosts = Math.pow(2.0, (32 - subnetBits).toDouble()).toInt() - 2

    return NetworkInformation(
        networkAddress = networkAddress,
        broadcastAddress = broadcastAddress,
        firstUsableAddress = firstUsableAddress,
        lastUsableAddress = lastUsableAddress,
        numberOfHosts = numberOfHosts
    )
}