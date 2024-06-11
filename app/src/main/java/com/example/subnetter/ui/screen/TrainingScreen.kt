package com.example.subnetter.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.subnetter.model.SubnetData.Companion.fromCidrToIp
import com.example.subnetter.ui.IpAddressInput
import com.example.subnetter.ui.IpAddressOutput
import com.example.subnetter.ui.theme.SubnetterTheme
import com.example.subnetter.util.calculateSubnet
import com.example.subnetter.util.generateRandomIpAddress
import com.example.subnetter.util.generateRandomSubnetMask
import com.example.subnetter.util.handleGenerateNewIpClick
import com.example.subnetter.util.handleSolveCidrClick
import com.example.subnetter.util.handleSolveClick
import com.example.subnetter.util.handleValidateCidrClick
import com.example.subnetter.util.handleValidateClick

/**
 * TrainingScreen is a Composable function that represents the main screen of the application.
 * It contains the UI elements for the IP address and subnet mask, as well as the input fields for the network, broadcast, first usable, and last usable IP addresses.
 * It also contains the buttons for validating, solving, and generating a new IP address.
 * The state of the UI elements and the logic for the buttons are managed using mutable state variables and helper functions from the util package.
 */
@Composable
fun TrainingScreen() {
    // Mutable state for the IP address and subnet mask
    var ipAddress by remember { mutableStateOf(generateRandomIpAddress()) }
    var subnetMask by remember { mutableStateOf(generateRandomSubnetMask()) }

    // Mutable state for the switch state and CIDR value
    var isCustomMask by remember { mutableStateOf(false) }
    var cidrValue by remember { mutableFloatStateOf(24f) }

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

    // Scroll state
    val scrollState = rememberScrollState()

    SubnetterTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp)
                    .verticalScroll(scrollState)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Custom Mask Switch
                Row(
                    modifier = Modifier.fillMaxWidth(0.83f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Custom Mask")
                    Switch(
                        checked = isCustomMask,
                        onCheckedChange = { isCustomMask = it }
                    )
                }
                // IP Address Output
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("IP Address")
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = ipAddress.toList()
                    )
                }
                // Subnet Mask Output or CIDR Slider
                Column(modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(0.83f)) {
                    Text(if (isCustomMask) "CIDR Notation" else "Subnet Mask")
                    if (isCustomMask) {
                        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            Text(
                                text = "/${cidrValue.toInt()}",
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Slider(
                            value = cidrValue,
                            onValueChange = { cidrValue = it },
                            valueRange = 0f..30f,
                            steps = 30
                        )
                        subnetMask = fromCidrToIp(cidrValue.toInt())
                    } else {
                        IpAddressOutput(
                            modifier = Modifier.padding(vertical = 5.dp),
                            value = subnetMask.toList()
                        )
                    }
                }
                // Divider
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    thickness = 1.dp,
                    color = Color.Gray
                )
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
                        if (isCustomMask) {
                            val networkInformation = calculateSubnet(ipAddress, fromCidrToIp(cidrValue.toInt()))
                            val borderColorsList = handleValidateCidrClick(networkInput, broadcastInput, firstUsableInput, lastUsableInput, networkInformation)
                            networkBorderColor = borderColorsList[0]
                            broadcastBorderColor = borderColorsList[1]
                            firstUsableBorderColor = borderColorsList[2]
                            lastUsableBorderColor = borderColorsList[3]
                        } else {
                            val borderColorsList = handleValidateClick(networkInput, broadcastInput, firstUsableInput, lastUsableInput, networkInfo)
                            networkBorderColor = borderColorsList[0]
                            broadcastBorderColor = borderColorsList[1]
                            firstUsableBorderColor = borderColorsList[2]
                            lastUsableBorderColor = borderColorsList[3]
                        }
                    }) {
                        Text("Validate")
                    }
                    Button(onClick = {
                        if (isCustomMask) {
                            val networkInformation = calculateSubnet(ipAddress, fromCidrToIp(cidrValue.toInt()))
                            val (inputs, borderColors) = handleSolveCidrClick(networkInformation)
                            networkInput = inputs[0]
                            broadcastInput = inputs[1]
                            firstUsableInput = inputs[2]
                            lastUsableInput = inputs[3]
                            networkBorderColor = borderColors[0]
                            broadcastBorderColor = borderColors[1]
                            firstUsableBorderColor = borderColors[2]
                            lastUsableBorderColor = borderColors[3]
                        } else {
                            val (inputs, borderColors) = handleSolveClick(networkInfo)
                            networkInput = inputs[0]
                            broadcastInput = inputs[1]
                            firstUsableInput = inputs[2]
                            lastUsableInput = inputs[3]
                            networkBorderColor = borderColors[0]
                            broadcastBorderColor = borderColors[1]
                            firstUsableBorderColor = borderColors[2]
                            lastUsableBorderColor = borderColors[3]
                        }
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

                        ipAddress = result.ipAddress
                        subnetMask = result.subnetMask

                        networkInfo = calculateSubnet(ipAddress, subnetMask)
                    }) {
                        Text("Generate New IP")
                    }
                }
                Spacer(modifier =  Modifier.height(48.dp))
            }
        }
    }
}