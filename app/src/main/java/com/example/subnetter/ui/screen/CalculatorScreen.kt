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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.subnetter.ui.IpAddressInput
import com.example.subnetter.ui.IpAddressOutput
import com.example.subnetter.ui.theme.SubnetterTheme

@Composable
fun CalculatorScreen(navController: NavController) {
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

                    }
                }
                // Subnet Mask Input
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Subnet Mask")
                    IpAddressInput(
                        modifier = Modifier.padding(vertical = 5.dp)
                    ) { octets ->

                    }
                }
                // Mask Notation Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { /* Handle Decimal */ }) {
                        Text("Decimal")
                    }
                    Button(onClick = { /* Handle Wildcard */ }) {
                        Text("Wildcard")
                    }
                    Button(onClick = { /* Handle CIDR */ }) {
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
                        value = listOf("0", "0", "0", "0")
                    )
                }
                // Broadcast Address Output
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Broadcast Address")
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = listOf("0", "0", "0", "0")
                    )
                }
                // First Usable Address Output
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("First Usable Address")
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = listOf("0", "0", "0", "0")
                    )
                }
                // Last Usable Address Output
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Last Usable Address")
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = listOf("0", "0", "0", "0")
                    )
                }
                // Number of Hosts
                Text("Number of Hosts: 0", modifier = Modifier.padding(top = 10.dp))
            }
        }
    }
}