package com.example.subnetter.ui

import android.annotation.SuppressLint
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
/**
 * Composable function that displays an IP address input field.
 *
 * The input field consists of four octets, each of which can be edited independently.
 * Each octet must be a number between 0 and 255.
 * The octets are separated by a dot.
 *
 * @param modifier The modifier to be applied to the input field.
 * @param value The current value of the IP address.
 * @param onValueChange The function to be called when the value of the IP address changes.
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun IpAddressInput(
    modifier: Modifier = Modifier,
    value: List<String>,
    onValueChange: (List<String>) -> Unit,
) {
    val octets = derivedStateOf { value.map { mutableStateOf(it) } }

    Row(modifier = modifier) {
        for (i in octets.value.indices) {
            Box(modifier = Modifier.background(Color.LightGray)){
                BasicTextField(
                    value = octets.value[i].value,
                    onValueChange = { newValue: String ->
                        if (newValue.isEmpty() || newValue.toIntOrNull() in 0..255) {
                            octets.value[i].value = newValue
                            onValueChange(octets.value.map { it.value })
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
            if (i < octets.value.size - 1) {
                Text("⬤", modifier = Modifier.padding(horizontal = 4.dp).offset(y = 14.dp))
            }
        }
    }
}
/**
 * Composable function that displays an IP address output field.
 *
 * The output field consists of four octets, each of which is displayed independently.
 * The octets are separated by a dot.
 *
 * @param modifier The modifier to be applied to the output field.
 * @param value The current value of the IP address.
 * @param backgroundColor The background color of the output field.
 */
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
                    textStyle = TextStyle(textAlign = TextAlign.Center, color = Color.White)
                )
            }
            if (i < value.size - 1) {
                Text("⬤", modifier = Modifier.padding(horizontal = 4.dp).offset(y = 14.dp))
            }
        }
    }
}