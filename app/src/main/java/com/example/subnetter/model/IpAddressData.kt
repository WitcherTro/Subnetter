package com.example.subnetter.model

/**
 * Data class representing an IP address.
 *
 * @property octet1 The first octet of the IP address.
 * @property octet2 The second octet of the IP address.
 * @property octet3 The third octet of the IP address.
 * @property octet4 The fourth octet of the IP address.
 */
data class IpAddress(val octet1: Int, val octet2: Int, val octet3: Int, val octet4: Int) {

    /**
     * Converts the IP address to a binary integer.
     *
     * @return The IP address as a binary integer.
     */
    fun toBinary(): Int {
        return (octet1 shl 24) or (octet2 shl 16) or (octet3 shl 8) or octet4
    }

    companion object {
        /**
         * Converts a binary integer to an IP address.
         *
         * @param binary The binary integer to convert.
         * @return The IP address.
         */
        fun fromBinary(binary: Int): IpAddress {
            val octet1 = binary shr 24 and 0xFF
            val octet2 = binary shr 16 and 0xFF
            val octet3 = binary shr 8 and 0xFF
            val octet4 = binary and 0xFF
            return IpAddress(octet1, octet2, octet3, octet4)
        }
    }

    /**
     * Converts the IP address to a list of strings.
     *
     * @return The IP address as a list of strings.
     */
    fun toList(): List<String> {
        return listOf(octet1.toString(), octet2.toString(), octet3.toString(), octet4.toString())
    }
}