package com.example.subnetter.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.subnetter.R
import com.example.subnetter.ui.theme.SubnetterTheme

@Composable
fun SubnetTable() {
    val data = listOf(
        SubnetData("/24", "255.255.255.0", "254"),
        SubnetData("/25", "255.255.255.128", "126"),
        SubnetData("/26", "255.255.255.192", "62"),
        // todo: change the data to be more dynamic later
    )

    val textColor = colorResource(R.color.white)
    val headerColor = colorResource(R.color.dark_gray)
    val rowColors = listOf(
        colorResource(R.color.gray),
        colorResource(R.color.dark_gray)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(headerColor)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "CIDR", modifier = Modifier.weight(0.5f), color = textColor)
            Text(text = "Subnet Mask", modifier = Modifier.weight(1f), color = textColor)
            Text(text = "Usable Hosts", modifier = Modifier.weight(1f), color = textColor)
        }

        data.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(rowColors[index % rowColors.size])
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = item.cidr, modifier = Modifier.weight(0.5f), color = textColor)
                Text(text = item.subnetMask, modifier = Modifier.weight(1f), color = textColor)
                Text(text = item.usableHosts, modifier = Modifier.weight(1f), color = textColor)
            }
        }
    }
}

data class SubnetData(
    val cidr: String,
    val subnetMask: String,
    val usableHosts: String
)
@Composable
fun TableScreen(navController: NavController) {
    SubnetterTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SubnetTable()
        }
    }
}