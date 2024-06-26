package com.example.subnetter.ui.screen

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.subnetter.R
import com.example.subnetter.data.IpAddress
import com.example.subnetter.data.NetworkInformation
import com.example.subnetter.ui.IpAddressInput
import com.example.subnetter.ui.IpAddressOutput
import com.example.subnetter.ui.theme.SubnetterTheme
import com.example.subnetter.util.calculateSubnet
import com.example.subnetter.util.handleCalculateClick
import com.example.subnetter.util.isValidIpAddress
import com.example.subnetter.util.isValidSubnetMask

/**
 * CalculatorScreen is a Composable function that provides the UI for the subnet calculator.
 * It includes input fields for the IP address and subnet mask, output fields for the network address,
 * broadcast address, first usable address, and last usable address, and buttons for calculating the subnet information,
 * switching between decimal and CIDR notation for the subnet mask, and dismissing the Snackbar.
 */
@Composable
fun CalculatorScreen() {
    // Mutable state for the IP address and subnet mask
    var ipAddress by rememberSaveable { mutableStateOf(listOf("", "", "", "")) }
    var subnetMask by rememberSaveable { mutableStateOf(listOf("", "", "", "")) }

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

    // Mutable state for the CIDR notation
    var isCIDR by rememberSaveable { mutableStateOf(false) }
    var cidrValue by rememberSaveable { mutableFloatStateOf(0f) }

    // Scroll state for the column
    val scrollState = rememberScrollState()

    val invalidIpOrSubnetMessage = stringResource(R.string.invalid_ip_address_or_subnet_mask)


    SubnetterTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(1.dp)
                .verticalScroll(scrollState)
                .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // IP Address Input
                Column(modifier = Modifier.padding(bottom = 5.dp)) {
                    Text(stringResource(R.string.ip_address))
                    IpAddressInput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = ipAddress.toList(),
                        onValueChange = { ipAddress = it }
                    )
                }
                // Subnet Mask Input
                Column(modifier = Modifier.padding(bottom = 5.dp)) {
                    if (isCIDR) {
                        Text(stringResource(R.string.cidr_notation), modifier = Modifier.align(Alignment.CenterHorizontally))
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
                            steps = 30,
                            modifier = Modifier
                                .fillMaxWidth(0.83f)
                                .align(Alignment.CenterHorizontally)
                        )

                    } else {
                        Text(stringResource(R.string.subnet_mask))
                        IpAddressInput(
                            modifier = Modifier.padding(vertical = 5.dp),
                            value = subnetMask.toList(),
                            onValueChange = { subnetMask = it }
                        )
                    }
                }
                // Mask Notation Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { isCIDR = false },
                        colors = ButtonDefaults.buttonColors(containerColor = if (!isCIDR) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.primary)
                    ) {
                        Text(stringResource(R.string.decimal))
                    }
                    Button(
                        onClick = { isCIDR = true },
                        colors = ButtonDefaults.buttonColors(containerColor = if (isCIDR) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.primary)
                    ) {
                        Text(stringResource(R.string.cidr))
                    }
                }
                // Divider
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    thickness = 1.dp,
                    color = Color.Gray
                )
                // Network Address Output
                Column(modifier = Modifier.padding(bottom = 5.dp)) {
                    Text(stringResource(R.string.network_address))
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = networkAddress
                    )
                }
                // Broadcast Address Output
                Column(modifier = Modifier.padding(bottom = 5.dp)) {
                    Text(stringResource(R.string.broadcast_address))
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = broadcastAddress
                    )
                }
                // First Usable Address Output
                Column(modifier = Modifier.padding(bottom = 5.dp)) {
                    Text(stringResource(R.string.first_usable_address))
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = firstUsableAddress
                    )
                }
                // Last Usable Address Output
                Column(modifier = Modifier.padding(bottom = 5.dp)) {
                    Text(stringResource(R.string.last_usable_address))
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = lastUsableAddress
                    )
                }
                Column(modifier = Modifier.padding(bottom = 5.dp)) {
                    Text(stringResource(R.string.usable_host_range, subnetInfo.numberOfHosts))
                }
                Button(
                    onClick = {
                        val result = handleCalculateClick(
                            ipAddress,
                            subnetMask,
                            isCIDR,
                            cidrValue,
                            ::isValidIpAddress,
                            ::isValidSubnetMask,
                            ::calculateSubnet
                        )
                        if (result != null) {
                            subnetInfo = result
                        } else {
                            snackbarMessage = invalidIpOrSubnetMessage
                            snackbarVisible = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    modifier = Modifier.padding(top = 5.dp)
                ) {
                    Text(stringResource(R.string.calculate))
                }
                if (snackbarVisible) {
                    Snackbar(
                        modifier = Modifier.padding(16.dp),
                        action = {
                            TextButton(onClick = { snackbarVisible = false }) {
                                Text(stringResource(R.string.dismiss))
                            }
                        }
                    ) {
                        Text(snackbarMessage)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}