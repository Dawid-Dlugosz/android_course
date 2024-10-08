package com.example.navexample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SecondScreen(
    name: String,
    age: Int,
    navigateToSecondScreen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Second Screen", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Welcome ${name}, age: ${age}", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = navigateToSecondScreen) {
            Text(
                text = "Go to second screen",
                fontSize = 18.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview(){
    SecondScreen("Dawid", 1, {})
}