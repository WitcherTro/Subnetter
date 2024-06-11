package com.example.subnetter.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.subnetter.model.IpAddress
import com.example.subnetter.model.NetworkInformation
import com.example.subnetter.model.toList
import com.example.subnetter.ui.IpAddressInput
import com.example.subnetter.ui.IpAddressOutput
import com.example.subnetter.ui.theme.SubnetterTheme
import com.example.subnetter.util.calculateSubnet
import com.example.subnetter.util.handleCIDRClick
import com.example.subnetter.util.handleDecimalClick
import com.example.subnetter.util.handleWildcardClick
import com.example.subnetter.util.isValidIpAddress

@Composable
fun CalculatorScreen() {
    // Mutable state for the IP address and subnet mask
    var ipAddress by remember { mutableStateOf(IpAddress(0, 0, 0, 0)) }
    var subnetMask by remember { mutableStateOf(IpAddress(0, 0, 0, 0)) }

    // Mutable state for the subnet information
    var subnetInfo by remember { mutableStateOf(NetworkInformation(
        networkAddress = IpAddress(0, 0, 0, 0),
        broadcastAddress = IpAddress(0, 0, 0, 0),
        firstUsableAddress = IpAddress(0, 0, 0, 0),
        lastUsableAddress = IpAddress(0, 0, 0, 0),
        numberOfHosts = 0
    )) }

    // Observe the subnetInfo state at the top level of the composable
    val networkAddress = subnetInfo.networkAddress.toList()
    val broadcastAddress = subnetInfo.broadcastAddress.toList()
    val firstUsableAddress = subnetInfo.firstUsableAddress.toList()
    val lastUsableAddress = subnetInfo.lastUsableAddress.toList()

    // Mutable state for the Snackbar visibility and message
    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    SubnetterTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // IP Address Input
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("IP Address")
                    IpAddressInput(
                        modifier = Modifier.padding(vertical = 5.dp)
                    ) { octets ->
                        if (octets.all { it.toIntOrNull() != null }) {
                            ipAddress = IpAddress(octets[0].toInt(), octets[1].toInt(), octets[2].toInt(), octets[3].toInt())
                        }
                    }
                }
                // Subnet Mask Input
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Subnet Mask")
                    IpAddressInput(
                        modifier = Modifier.padding(vertical = 5.dp)
                    ) { octets ->
                        if (octets.all { it.toIntOrNull() != null }) {
                            subnetMask = IpAddress(octets[0].toInt(), octets[1].toInt(), octets[2].toInt(), octets[3].toInt())
                        }
                    }
                }
                // Mask Notation Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { handleDecimalClick() }) {
                        Text("Decimal")
                    }
                    Button(onClick = { handleWildcardClick() }) {
                        Text("Wildcard")
                    }
                    Button(onClick = { handleCIDRClick() }) {
                        Text("CIDR")
                    }
                }
                // Divider
                Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                // Network Address Output
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Network Address")
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = networkAddress
                    )
                }
// Broadcast Address Output
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Broadcast Address")
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = broadcastAddress
                    )
                }
// First Usable Address Output
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("First Usable Address")
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = firstUsableAddress
                    )
                }
// Last Usable Address Output
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Last Usable Address")
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = lastUsableAddress
                    )
                }
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Usable Host Range: ${subnetInfo.numberOfHosts}")
                }
                Button(
                    onClick = {
                        // Validate the IP address and subnet mask
                        if (isValidIpAddress(ipAddress) && isValidIpAddress(subnetMask)) {
                            // Calculate the subnet information and update the state
                            subnetInfo = calculateSubnet(ipAddress, subnetMask)
                        } else {
                            // Show error message
                            snackbarMessage = "Invalid IP address or subnet mask."
                            snackbarVisible = true
                        }
                    },
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Text("Calculate")
                }
                if (snackbarVisible) {
                    Snackbar(
                        modifier = Modifier.padding(16.dp),
                        action = {
                            TextButton(onClick = { snackbarVisible = false }) {
                                Text("Dismiss")
                            }
                        }
                    ) {
                        Text(snackbarMessage)
                    }
                }
            }
        }
    }
}