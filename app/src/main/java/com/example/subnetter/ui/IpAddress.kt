package com.example.subnetter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun IpAddressInput(
    modifier: Modifier = Modifier,
    onValueChange: (List<String>) -> Unit
) {
    val octets = remember { List(4) { mutableStateOf("") } }

    Row(modifier = modifier) {
        for (i in octets.indices) {
            TextField(
                value = octets[i].value,
                onValueChange = { newValue: String ->
                    if (newValue.isEmpty() || newValue.toIntOrNull() in 0..255) {
                        octets[i].value = newValue
                        onValueChange(octets.map { it.value })
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                maxLines = 1,
                modifier = Modifier.width(65.dp)
            )
            if (i < octets.size - 1) {
                Text("⬤", modifier = Modifier.padding(horizontal = 4.dp).offset(y = 32.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IpAddressOutput(
    modifier: Modifier = Modifier,
    value: List<String>,
    backgroundColor: Color = Color.DarkGray,
) {
    val octets = remember { value.map { mutableStateOf(it) } }

    Row(modifier = modifier) {
        for (i in octets.indices) {
            Box(modifier = Modifier.background(backgroundColor)) {
                TextField(
                    value = octets[i].value,
                    onValueChange = { },
                    readOnly = true,
                    modifier = Modifier.width(65.dp)
                )
            }
            if (i < octets.size - 1) {
                Text("⬤", modifier = Modifier.padding(horizontal = 4.dp).offset(y = 32.dp))
            }
        }
    }
}