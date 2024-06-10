package com.example.subnetter.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                Text(
                    "Training Screen",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                IpAddressInput(
                    modifier = Modifier.padding(vertical = 20.dp)
                ) { octets ->

                }
                IpAddressOutput(
                    modifier = Modifier.padding(vertical = 20.dp),
                    value = listOf("192", "168", "1", "1")
                )
            }
        }
    }
}