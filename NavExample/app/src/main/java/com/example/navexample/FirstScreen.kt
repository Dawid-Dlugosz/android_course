package com.example.navexample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun FirstScreen(navigateToSecondScreen: (String, Int) -> Unit) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("0") }

    Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(text = "Enter name", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = name, onValueChange = {
            name = it
        })
        OutlinedTextField(value = age, onValueChange = {
            age = it
        })
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navigateToSecondScreen(
                    name,
                    age.toInt()
                )
            }
        ) {
            Text(
                text = "Go to second screen",
                fontSize = 18.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    FirstScreen { String, int -> }
}