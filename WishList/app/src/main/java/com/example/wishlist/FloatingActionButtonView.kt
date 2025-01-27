package com.example.wishlist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionButtonView(
    onTap: () -> Unit
) {
    FloatingActionButton(
        modifier = Modifier.padding(20.dp),
        contentColor = Color.White,
        containerColor = Color.Black,
        onClick = onTap
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Dodaj")
    }
}