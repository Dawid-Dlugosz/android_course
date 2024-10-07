package com.example.capitangame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.capitangame.ui.theme.CapitanGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapitanGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CapitanGame()
                }
            }
        }
    }
}

@Composable
fun CapitanGame() {

    val treasuredFound = remember {
        mutableStateOf(0)
    }
    val direction = remember {
        mutableStateOf("West")
    }
    val stormOrTreasure = remember {
        mutableStateOf("")
    }


    Column {
        Text("Founded treasured: ${treasuredFound.value}")
        Text("Current Direction: ${direction.value}")
        Text(stormOrTreasure.value)
        Button(onClick = {
            direction.value = "North"
            if(Random.nextBoolean()) {
                treasuredFound.value += 1
                stormOrTreasure.value = "Found treasure"
            }else{
                stormOrTreasure.value = "storm ahead"
            }

        }) {
            Text("North")
        }

        Button(onClick = {
            direction.value = "East"
            if(Random.nextBoolean()) {
                treasuredFound.value += 1
                stormOrTreasure.value = "Found treasure"
            }else{
                stormOrTreasure.value = "storm ahead"
            }
        }) {
            Text("East")
        }

        Button(onClick = {
            direction.value = "South"
            if(Random.nextBoolean()) {
                treasuredFound.value += 1
                stormOrTreasure.value = "Found treasure"
            }else{
                stormOrTreasure.value = "storm ahead"
            }
        }) {
            Text("South")
        }

        Button(onClick = {
            direction.value = "West"
            if(Random.nextBoolean()) {
                treasuredFound.value += 1
                stormOrTreasure.value = "Found treasure"
            }else{
                stormOrTreasure.value = "storm ahead"
            }
        }) {
            Text("West")
        }
    }
}