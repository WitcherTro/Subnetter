package com.example.subnetter.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
                Text(".", modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}