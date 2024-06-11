package com.example.subnetter.data

/**
 * Data class representing the network information.
 *
 * @property networkAddress The network IP address.
 * @property broadcastAddress The broadcast IP address.
 * @property firstUsableAddress The first usable IP address in the network.
 * @property lastUsableAddress The last usable IP address in the network.
 * @property numberOfHosts The number of hosts that can be accommodated in the network.
 */
data class NetworkInformation(
    val networkAddress: IpAddress,
    val broadcastAddress: IpAddress,
    val firstUsableAddress: IpAddress,
    val lastUsableAddress: IpAddress,
    val numberOfHosts: Long
)