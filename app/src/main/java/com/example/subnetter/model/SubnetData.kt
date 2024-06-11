package com.example.subnetter.model

import kotlin.math.pow

/**
 * Data class representing the subnet data.
 *
 * @property cidr The CIDR notation of the subnet mask.
 * @property subnetMask The subnet mask.
 * @property usableHosts The number of usable hosts in the subnet.
 */
data class SubnetData(
    val cidr: String,
    val subnetMask: String,
    val usableHosts: String
) {
    companion object {
        /**
         * Converts a CIDR value to a SubnetData object.
         *
         * This function takes a CIDR value as input and returns a SubnetData object representing the subnet data corresponding to that CIDR value.
         * If the CIDR value is not valid (i.e., not in the range 0-32), it returns null.
         *
         * @param cidr The CIDR value to convert.
         * @return The SubnetData object representing the subnet data, or null if the CIDR value is invalid.
         */
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

        /**
         * Converts a CIDR value to an IpAddress object.
         *
         * This function takes a CIDR value as input and returns an IpAddress object representing the subnet mask corresponding to that CIDR value.
         * If the CIDR value is not valid (i.e., not in the range 0-32), it returns an IpAddress object with all octets set to 0.
         *
         * @param cidr The CIDR value to convert.
         * @return The IpAddress object representing the subnet mask.
         */
        fun fromCidrToIp(cidr: Int): IpAddress {
            from(cidr)?.let {
                val parts = it.subnetMask.split(".").map { it.toInt() }
                return IpAddress(parts[0], parts[1], parts[2], parts[3])
            }
            return IpAddress(0, 0, 0, 0)
        }
    }
}