package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    val inputValue =  remember {
        mutableStateOf("")
    }
    val outputValue = remember {
        mutableStateOf("")
    }
    val inputEnabled = remember {
        mutableStateOf(false)
    }
    val outputEnabled = remember {
        mutableStateOf(false)
    }
    val inputUnit = remember {
        mutableStateOf("Meters")
    }
    val outputUnit = remember {
        mutableStateOf("Meters")
    }
    val conversionFactory = remember {
        mutableStateOf(1.0)
    }
    val oConversionFactory = remember {
        mutableStateOf(1.0)
    }

    fun convertUnit() {
        val inputDouble = inputValue.value.toDoubleOrNull() ?: 0.0
        val result = (inputDouble * conversionFactory.value * 100.0 / oConversionFactory.value).roundToInt() / 100.0
        outputValue.value = result.toString()
    }

    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge
            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue.value,
            onValueChange = {
                inputValue.value = it
                convertUnit()
            },
            label =  { Text("Enter Value")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box{
                Button(onClick = {
                    inputEnabled.value = !inputEnabled.value
                }) {
                    Text(inputUnit.value)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        "Arrow Dropdown"
                    )
                }
                DropdownMenu(expanded = inputEnabled.value, onDismissRequest = { inputEnabled.value = !inputEnabled.value }) {
                    DropdownMenuItem(
                        {Text("Centimeters")},
                        onClick = {
                            inputEnabled.value = false
                            inputUnit.value = "Centimeters"
                            conversionFactory.value = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        {Text("Meters")},
                        onClick = {
                            inputEnabled.value = false
                            inputUnit.value = "Meters"
                            conversionFactory.value = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        {Text("Feet")},
                        onClick = {
                            inputEnabled.value = false
                            inputUnit.value = "Feet"
                            conversionFactory.value = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        {Text("Millimeters")},
                        onClick = {
                            inputEnabled.value = false
                            inputUnit.value = "Millimeters"
                            conversionFactory.value = 0.001
                            convertUnit()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick = { outputEnabled.value = !outputEnabled.value }) {
                    Text(outputUnit.value)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        "Arrow Dropdown"
                    )
                }
                DropdownMenu(expanded = outputEnabled.value, onDismissRequest = { outputEnabled.value = !outputEnabled.value }) {
                    DropdownMenuItem(
                        {Text("Centimeters")},
                        onClick = {
                            outputEnabled.value = false
                            outputUnit.value = "Centimeters"
                            oConversionFactory.value = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        {Text("Meters")},
                        onClick = {
                            outputEnabled.value = false
                            outputUnit.value = "Meters"
                            oConversionFactory.value = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        {Text("Feet")},
                        onClick = {
                            outputEnabled.value = false
                            outputUnit.value = "Feet"
                            oConversionFactory.value = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        {Text("Millimeters")},
                        onClick = {
                            outputEnabled.value = false
                            outputUnit.value = "Millimeters"
                            oConversionFactory.value = 0.001
                            convertUnit()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Result: ${outputValue.value}",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}