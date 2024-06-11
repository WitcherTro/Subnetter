package com.example.subnetter.util

import com.example.subnetter.model.IpAddress
import com.example.subnetter.model.NetworkInformation
import com.example.subnetter.model.SubnetData

fun handleCalculateClick(
    ipAddress: IpAddress?,
    subnetMask: IpAddress?,
    isCIDR: Boolean,
    cidrValue: Float,
    isValidIpAddress: (IpAddress) -> Boolean,
    isValidSubnetMask: (IpAddress) -> Boolean,
    calculateSubnet: (IpAddress, IpAddress) -> NetworkInformation
): NetworkInformation? {
    // Check if the IP address is not null
    if (ipAddress != null) {
        // Validate the IP address
        if (isValidIpAddress(ipAddress)) {
            var mask: IpAddress
            if (isCIDR) {
                // Calculate the subnet mask from the CIDR value
                mask = SubnetData.fromCidrToIp(cidrValue.toInt())
                // Calculate the subnet information and return it
                return calculateSubnet(ipAddress, mask)
            } else if (subnetMask != null && isValidSubnetMask(subnetMask)) {
                // Calculate the subnet information and return it
                return calculateSubnet(ipAddress, subnetMask)
            }
        }
    }
    // Return null if the IP address is null or invalid
    return null
}