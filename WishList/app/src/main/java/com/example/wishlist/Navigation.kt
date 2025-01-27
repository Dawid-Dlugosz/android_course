package com.example.wishlist

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(
    viewModel: WishViewModel = viewModel(), controller: NavHostController = rememberNavController()
) {
    NavHost(
        navController = controller, startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeView(
                viewModel = viewModel,
                navController = controller,
            )
        }

        composable(Screen.AddScreen.route + "/{id}", arguments =  listOf(
            navArgument("id") {
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            }
        )) {
            val id = if(it.arguments != null)  it.arguments!!.getLong("id") else 0L

            println(id)
            AddEditWishView(
                id = id,
                viewModel = viewModel,
                navController = controller,
            )
        }
    }
}