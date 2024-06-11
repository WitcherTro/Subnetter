package com.example.subnetter.model

data class NetworkInformation(
    val networkAddress: IpAddress,
    val broadcastAddress: IpAddress,
    val firstUsableAddress: IpAddress,
    val lastUsableAddress: IpAddress,
    val numberOfHosts: Long
)
