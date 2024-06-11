package com.example.subnetter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun IpAddressInput(
    modifier: Modifier = Modifier,
    onValueChange: (List<String>) -> Unit,
) {
    val octets = remember { List(4) { mutableStateOf("") } }

    Row(modifier = modifier) {
        for (i in octets.indices) {
            Box(modifier = Modifier.background(Color.LightGray)){
                BasicTextField(
                    value = octets[i].value,
                    onValueChange = { newValue: String ->
                        if (newValue.isEmpty() || newValue.toIntOrNull() in 0..255) {
                            octets[i].value = newValue
                            onValueChange(octets.map { it.value })
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    singleLine = true,
                    modifier = Modifier.width(65.dp)
                        .height(32.dp)
                        .padding(7.dp),
                    textStyle = TextStyle(textAlign = TextAlign.Center)
                )
            }
            if (i < octets.size - 1) {
                Text("⬤", modifier = Modifier.padding(horizontal = 4.dp).offset(y = 14.dp))
            }
        }
    }
}

@Composable
fun IpAddressOutput(
    modifier: Modifier = Modifier,
    value: List<String>,
    backgroundColor: Color = Color.Gray,
) {
    Row(modifier = modifier) {
        for (i in value.indices) {
            Box(modifier = Modifier.background(backgroundColor), contentAlignment = Alignment.Center) {
                BasicTextField(
                    value = value[i],
                    onValueChange = { },
                    readOnly = true,
                    modifier = Modifier.width(65.dp)
                        .height(32.dp)
                        .padding(7.dp),
                    textStyle = TextStyle(textAlign = TextAlign.Center)
                )
            }
            if (i < value.size - 1) {
                Text("⬤", modifier = Modifier.padding(horizontal = 4.dp).offset(y = 14.dp))
            }
        }
    }
}