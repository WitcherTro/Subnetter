package com.example.subnetter.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.subnetter.ui.IpAddressInput
import com.example.subnetter.ui.IpAddressOutput
import com.example.subnetter.ui.theme.SubnetterTheme
import com.example.subnetter.util.calculateSubnet
import com.example.subnetter.util.generateRandomIpAddress
import com.example.subnetter.util.generateRandomSubnetMask
import com.example.subnetter.util.handleGenerateNewIpClick
import com.example.subnetter.util.handleSolveClick
import com.example.subnetter.util.handleValidateClick

@Composable
fun TrainingScreen() {
    // Mutable state for the IP address and subnet mask
    var ipAddress by remember { mutableStateOf(generateRandomIpAddress()) }
    var subnetMask by remember { mutableStateOf(generateRandomSubnetMask()) }

    // Mutable state for the user's input
    var networkInput by remember { mutableStateOf(listOf("", "", "", "")) }
    var broadcastInput by remember { mutableStateOf(listOf("", "", "", "")) }
    var firstUsableInput by remember { mutableStateOf(listOf("", "", "", "")) }
    var lastUsableInput by remember { mutableStateOf(listOf("", "", "", "")) }

    // Mutable state for the input field border color
    var networkBorderColor by remember { mutableStateOf(Color.Transparent) }
    var broadcastBorderColor by remember { mutableStateOf(Color.Transparent) }
    var firstUsableBorderColor by remember { mutableStateOf(Color.Transparent) }
    var lastUsableBorderColor by remember { mutableStateOf(Color.Transparent) }

    // Network information
    var networkInfo by remember { mutableStateOf(calculateSubnet(ipAddress, subnetMask)) }

    SubnetterTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // IP Address Output
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("IP Address")
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = ipAddress.toList()
                    )
                }
                // Subnet Mask Output
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Subnet Mask")
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = subnetMask.toList()
                    )
                }
                // Divider
                Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                // Network Address Input
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Network Address")
                    IpAddressInput(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .border(2.dp, networkBorderColor),
                        value = networkInput,
                        onValueChange = { networkInput = it }
                    )
                }
                // Broadcast Address Input
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Broadcast Address")
                    IpAddressInput(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .border(2.dp, broadcastBorderColor),
                        value = broadcastInput,
                        onValueChange = { broadcastInput = it }
                    )
                }
                // First Usable Address Input
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("First Usable Address")
                    IpAddressInput(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .border(2.dp, firstUsableBorderColor),
                        value = firstUsableInput,
                        onValueChange = { firstUsableInput = it }
                    )
                }
                // Last Usable Address Input
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Last Usable Address")
                    IpAddressInput(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .border(2.dp, lastUsableBorderColor),
                        value = lastUsableInput,
                        onValueChange = { lastUsableInput = it }
                    )
                }
                // Validate, Solve and Generate New IP Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        val borderColorsList = handleValidateClick(networkInput, broadcastInput, firstUsableInput, lastUsableInput, networkInfo)
                        networkBorderColor = borderColorsList[0]
                        broadcastBorderColor = borderColorsList[1]
                        firstUsableBorderColor = borderColorsList[2]
                        lastUsableBorderColor = borderColorsList[3]
                    }) {
                        Text("Validate")
                    }
                    Button(onClick = {
                        val (inputs, borderColors) = handleSolveClick(networkInfo)
                        networkInput = inputs[0]
                        broadcastInput = inputs[1]
                        firstUsableInput = inputs[2]
                        lastUsableInput = inputs[3]
                        networkBorderColor = borderColors[0]
                        broadcastBorderColor = borderColors[1]
                        firstUsableBorderColor = borderColors[2]
                        lastUsableBorderColor = borderColors[3]
                    }) {
                        Text("Solve")
                    }
                    Button(onClick = {
                        val result = handleGenerateNewIpClick()
                        networkInput = result.inputs[0]
                        broadcastInput = result.inputs[1]
                        firstUsableInput = result.inputs[2]
                        lastUsableInput = result.inputs[3]
                        networkBorderColor = result.borderColors[0]
                        broadcastBorderColor = result.borderColors[1]
                        firstUsableBorderColor = result.borderColors[2]
                        lastUsableBorderColor = result.borderColors[3]

                        // Update the IP address and subnet mask with the new values
                        ipAddress = result.ipAddress
                        subnetMask = result.subnetMask

                        // Calculate the new network information
                        networkInfo = calculateSubnet(ipAddress, subnetMask)
                    }) {
                        Text("Generate New IP")
                    }
                }
            }
        }
    }
}