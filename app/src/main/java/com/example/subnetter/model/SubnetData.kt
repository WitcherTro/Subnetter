package com.example.subnetter.model

import kotlin.math.pow

data class SubnetData(
    val cidr: String,
    val subnetMask: String,
    val usableHosts: String
) {
    companion object {
        fun from(cidr: Int): SubnetData? {
            if (cidr !in 0..32) {
                return null
            }

            val subnetMask = (0xFFFFFFFFL shl (32 - cidr)).let {
                "${it shr 24 and 0xFF}.${it shr 16 and 0xFF}.${it shr 8 and 0xFF}.${it and 0xFF}"
            }
            val usableHosts = if (cidr < 31) (2.0.pow((32 - cidr).toDouble()) - 2).toLong().toString() else "0"

            return SubnetData("/$cidr", subnetMask, usableHosts)
        }

        fun fromCidrToIp(cidr: Int): IpAddress {
            from(cidr)?.let {
                val parts = it.subnetMask.split(".").map { it.toInt() }
                return IpAddress(parts[0], parts[1], parts[2], parts[3])
            }
            return IpAddress(0, 0, 0, 0)
        }
    }
}