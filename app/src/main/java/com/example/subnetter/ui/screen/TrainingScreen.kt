package com.example.subnetter.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
fun TrainingScreen(navController: NavController) {
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
                        value = listOf("192", "168", "1", "1")
                    )
                }
                // Subnet Mask Output
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Subnet Mask")
                    IpAddressOutput(
                        modifier = Modifier.padding(vertical = 5.dp),
                        value = listOf("255", "255", "255", "0")
                    )
                }
                // Divider
                Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                // Network Address Input
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Network Address")
                    IpAddressInput(
                        modifier = Modifier.padding(vertical = 5.dp)
                    ) { octets ->

                    }
                }
                // Broadcast Address Input
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Broadcast Address")
                    IpAddressInput(
                        modifier = Modifier.padding(vertical = 5.dp)
                    ) { octets ->

                    }
                }
                // First Usable Address Input
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("First Usable Address")
                    IpAddressInput(
                        modifier = Modifier.padding(vertical = 5.dp)
                    ) { octets ->

                    }
                }
                // Last Usable Address Input
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text("Last Usable Address")
                    IpAddressInput(
                        modifier = Modifier.padding(vertical = 5.dp)
                    ) { octets ->

                    }
                }
                // Validate and Solve Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { /* Handle Validate */ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Green, contentColor = Color.Black)) {
                        Text("Validate")
                    }
                    Button(onClick = { /* Handle Solve */ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
                        Text("Solve")
                    }
                }
            }
        }
    }
}