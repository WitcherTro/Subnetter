package com.example.subnetter.model

data class IpAddress(val octet1: Int, val octet2: Int, val octet3: Int, val octet4: Int) {
    fun toBinary(): Int {
        return (octet1 shl 24) or (octet2 shl 16) or (octet3 shl 8) or octet4
    }

    companion object {
        fun fromBinary(binary: Int): IpAddress {
            val octet1 = binary shr 24 and 0xFF
            val octet2 = binary shr 16 and 0xFF
            val octet3 = binary shr 8 and 0xFF
            val octet4 = binary and 0xFF
            return IpAddress(octet1, octet2, octet3, octet4)
        }
    }
}

fun IpAddress.toList(): List<String> {
    return listOf(octet1.toString(), octet2.toString(), octet3.toString(), octet4.toString())
}
