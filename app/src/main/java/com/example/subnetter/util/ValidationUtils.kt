package com.example.subnetter.util

import  com.example.subnetter.model.IpAddress

fun isValidIpAddress(ipAddress: IpAddress): Boolean {
    return ipAddress.octet1 in 0..255 &&
            ipAddress.octet2 in 0..255 &&
            ipAddress.octet3 in 0..255 &&
            ipAddress.octet4 in 0..255
}