package com.example.subnetter.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.subnetter.R
import com.example.subnetter.data.SubnetData
import com.example.subnetter.ui.theme.SubnetterTheme

/**
 * Composable function that displays a table of subnet data.
 *
 * The table includes columns for CIDR, subnet mask, and usable hosts.
 * Each row in the table represents a different subnet.
 */
@Composable
fun SubnetTable() {
    val data = generateSubnetDataList()
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
            Text(text = stringResource(R.string.cidr), modifier = Modifier.weight(0.5f), color = textColor)
            Text(text = stringResource(R.string.subnet_mask), modifier = Modifier.weight(1f), color = textColor)
            Text(text = stringResource(R.string.usable_hosts), modifier = Modifier.weight(1f), color = textColor)
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

/**
 * Composable function that displays the Table screen.
 *
 * The screen includes a scrollable table of subnet data.
 */
@Composable
fun TableScreen() {
    val scrollState = rememberScrollState()

    SubnetterTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            color = MaterialTheme.colorScheme.background
        ) {
            SubnetTable()
        }
    }
}

/**
 * Generates a list of SubnetData objects for CIDR values from 0 to 30.
 *
 * @return A list of SubnetData objects.
 */
fun generateSubnetDataList(): List<SubnetData> {
    return (0..30).mapNotNull { cidr ->
        SubnetData.fromCidrToData(cidr)
    }
}