package com.example.navexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navexample.ui.theme.NavExampleTheme


enum class Routes{
    FirstScreen,
    SecondScreen,
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}


@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.FirstScreen.name
    ) {
        composable(route = Routes.FirstScreen.name) {
            FirstScreen {
                name, age ->
                println("asdfsdffsda ${age}")
                navController.navigate("${Routes.SecondScreen.name}/${name}/${age}")
            }
        }
        composable(route = "${Routes.SecondScreen.name}/{name}/{age}") {

            val name = it.arguments?.getString("name") ?: "no name"
            val age = it.arguments?.getString("age") ?: "0"

            SecondScreen(
                name = name,
                age = age.toInt()
            ) {
                navController.navigate(Routes.FirstScreen.name)
            }
        }
    }
}